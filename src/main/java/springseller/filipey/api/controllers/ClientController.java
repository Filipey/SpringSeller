package springseller.filipey.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springseller.filipey.domain.Client;
import springseller.filipey.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientsRepository.findById(id);

        if (!client.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.get());
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client) {
        Client savedClient = clientsRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        Optional<Client> client = clientsRepository.findById(id);

        if (!client.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        clientsRepository.delete(client.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Client client) {
        Optional<Client> updatedClient = clientsRepository.findById(id);

        if (!updatedClient.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        client.setId(updatedClient.get().getId());
        clientsRepository.save(client);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Client>> find( Client filtredClient ) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtredClient, matcher);

        List<Client> list = clientsRepository.findAll(example);

        return ResponseEntity.ok(list);
    }
}
