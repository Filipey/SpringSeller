package springseller.filipey.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springseller.filipey.domain.Product;
import springseller.filipey.repositories.ProductsRepository;
import springseller.filipey.services.ProductService;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public Product insert(Product product) {
        return productsRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productsRepository
                .findById(id)
                .map(product -> {
                    productsRepository.delete(product);
                    return product;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public void update(Long id, Product product) {
        productsRepository
                .findById(id)
                .map(existingProduct -> {
                    product.setId(existingProduct.getId());
                    productsRepository.save(product);
                    return product;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public List<Product> find(Product filteredProduct) {
        ExampleMatcher matcher =
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filteredProduct, matcher);

        if (productsRepository.findAll(example).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return productsRepository.findAll(example);
    }
}
