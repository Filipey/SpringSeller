package springseller.webservice.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import springseller.webservice.domain.Client;
import springseller.webservice.services.ClientService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/v1/clients")
@Api("Clients API")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a Client Info")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a Client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client created"),
            @ApiResponse(code = 400, message = "Authorization Error")
    })
    public Client insert(@RequestBody @Valid Client client) {
        return clientService.insert(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a Client")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Client deleted"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a Client")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Client updated"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public void update(@PathVariable Long id, @RequestBody @Valid Client client) {
        clientService.update(id, client);
    }

    @GetMapping
    @ApiOperation("Get a list of Clients")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clients found"),
            @ApiResponse(code = 404, message = "No Client found")
    })
    public List<Client> find( Client filteredClient ) {
        return clientService.find(filteredClient);
    }

}
