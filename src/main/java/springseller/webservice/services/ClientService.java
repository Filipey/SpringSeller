package springseller.webservice.services;

import springseller.webservice.domain.Client;

import java.util.List;

public interface ClientService {
    Client getClientById(Long id);

    Client insert(Client client);

    void delete(Long id);

    void update(Long id, Client client);

    List<Client> find(Client filteredClient);

}
