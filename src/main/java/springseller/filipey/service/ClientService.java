package springseller.filipey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springseller.filipey.model.Client;
import springseller.filipey.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository = new ClientRepository();

    public void save(Client client) {
        validate(client);
        clientRepository.save(client);
    }

    public void validate(Client client) {

    }
}
