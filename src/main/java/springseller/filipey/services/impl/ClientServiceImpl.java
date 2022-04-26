package springseller.filipey.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springseller.filipey.domain.Client;
import springseller.filipey.repositories.ClientRepository;
import springseller.filipey.services.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientsRepository;

    @Override
    @Transactional
    public Client getClientById(Long id) {
        return clientsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @Override
    public Client insert(Client client) {
        return clientsRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientsRepository.findById(id)
                .map(client -> {
                    clientsRepository.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @Override
    public void update(Long id, Client client) {
        clientsRepository.findById(id)
                .map(existingClient -> {
                    client.setId(existingClient.getId());
                    clientsRepository.save(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @Override
    public List<Client> find(Client filteredClient) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filteredClient, matcher);

        return clientsRepository.findAll(example);
    }


}