package springseller.filipey.api.controllers;

import org.springframework.web.bind.annotation.*;
import springseller.filipey.domain.Client;
import springseller.filipey.services.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;



@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    @ResponseStatus()
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client insert(@RequestBody Client client) {
        return clientService.insert(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody Client client) {
        clientService.update(id, client);
    }

    @GetMapping
    public List<Client> find( Client filteredClient ) {
        return clientService.find(filteredClient);
    }

}
