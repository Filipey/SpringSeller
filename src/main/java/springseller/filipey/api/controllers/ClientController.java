package springseller.filipey.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springseller.filipey.domain.Client;
import springseller.filipey.repositories.ClientRepository;

import java.util.List;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientsRepository;

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client insert(@RequestBody Client client) {
        return clientsRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientsRepository.findById(id)
                .map(client -> {
                    clientsRepository.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody Client client) {
        clientsRepository.findById(id)
                .map(existingClient -> {
                    client.setId(existingClient.getId());
                    clientsRepository.save(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Client> find( Client filteredClient ) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filteredClient, matcher);

        return clientsRepository.findAll(example);
    }
}
