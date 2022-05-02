package springseller.webservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springseller.webservice.api.exception.ClientNotFoundException;
import springseller.webservice.domain.Client;
import springseller.webservice.repositories.ClientRepository;
import springseller.webservice.services.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientsRepository;

    @Override
    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ClientNotFoundException());
    }

    @Override
    @Transactional
    public Client insert(Client client) {
        return clientsRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientsRepository.findById(id)
                .map(client -> {
                    clientsRepository.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ClientNotFoundException());
    }

    @Override
    @Transactional
    public void update(Long id, Client client) {
        clientsRepository.findById(id)
                .map(existingClient -> {
                    client.setId(existingClient.getId());
                    clientsRepository.save(client);
                    return client;
                }).orElseThrow(() -> new ClientNotFoundException());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> find(Client filteredClient) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filteredClient, matcher);

        return clientsRepository.findAll(example);
    }


}
