package springseller.webservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import springseller.webservice.api.dto.*;
import springseller.webservice.api.exception.ObjectNotFoundException;
import springseller.webservice.api.exception.RequestNotFoundException;
import springseller.webservice.domain.Client;
import springseller.webservice.domain.Product;
import springseller.webservice.domain.ProductRequest;
import springseller.webservice.domain.Request;
import springseller.webservice.domain.enums.RequestStatus;
import springseller.webservice.repositories.ClientRepository;
import springseller.webservice.repositories.ProductRequestRepository;
import springseller.webservice.repositories.ProductsRepository;
import springseller.webservice.repositories.RequestRepository;
import springseller.webservice.services.RequestService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final ClientRepository clientRepository;
    private final ProductsRepository productsRepository;
    private final ProductRequestRepository productRequestRepository;


    @Override
    @Transactional
    public Request save(RequestDTO dto) {
        Long clientId = dto.getClient();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid client id."));

        Request request = new Request();
        request.setAmount(dto.getAmount());
        request.setRequestDate(LocalDate.now());
        request.setClient(client);

        List<ProductRequest> productRequests = toEntity(request, dto.getRequests());
        requestRepository.save(request);
        request.setStatus(RequestStatus.ACCOMPLISHED);

        productRequestRepository.saveAll(productRequests);
        request.setProducts(productRequests);

        return request;
    }

    private List<ProductRequest> toEntity(Request request, List<ProductRequestDTO> products) {
        if (products.isEmpty()) {
            throw new ObjectNotFoundException("Cannot make a request without products");
        }

        return products
                .stream()
                .map( dto -> {
                    Long productId = dto.getProduct();
                    Product product = productsRepository
                            .findById(productId)
                            .orElseThrow(() -> new ObjectNotFoundException("Invalid product id: " + productId));

                    ProductRequest productRequest = new ProductRequest();
                    productRequest.setQuantity(dto.getQuantity());
                    productRequest.setRequest(request);
                    productRequest.setProduct(product);

                    return productRequest;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Request> getFullRequest(Long id) {
        return requestRepository.findByIdFetchProducts(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RequestInfoDTO getById(Long id) {
        return getFullRequest(id)
                .map(p -> infoDtoToEntity(p))
                .orElseThrow(() ->
                        new RequestNotFoundException());
    }

    private List<ProductRequestInfoDTO> productsRequestsToDto(List<ProductRequest> productRequests) {
        if(CollectionUtils.isEmpty(productRequests)) {
            return Collections.emptyList();
        }

        return productRequests.stream().map(
                p -> ProductRequestInfoDTO
                        .builder()
                        .description(p.getProduct().getDescription())
                        .unitPrice(p.getProduct().getPrice())
                        .quantity(p.getQuantity())
                        .build()
        ).collect(Collectors.toList());
    }

    private RequestInfoDTO infoDtoToEntity(Request request) {
        return RequestInfoDTO
                .builder()
                .id(request.getId())
                .requestDate(request.getRequestDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(request.getClient().getCpf())
                .clientName(request.getClient().getName())
                .amount(request.getAmount())
                .status(request.getStatus().name())
                .products(productsRequestsToDto(request.getProducts()))
                .build();
    }

    private void setStatus(Long id, RequestStatus requestStatus) {
        requestRepository
                .findById(id)
                .map( p -> {
                    p.setStatus(requestStatus);
                    return requestRepository.save(p);
                } ).orElseThrow(() -> new RequestNotFoundException());
    }

    @Override
    @Transactional
    public void updateStatus(Long id, RequestStatusUpdateDTO dto) {
        String newStatus = dto.getNewStatus();
        setStatus(id, RequestStatus.valueOf(newStatus));
    }
}
