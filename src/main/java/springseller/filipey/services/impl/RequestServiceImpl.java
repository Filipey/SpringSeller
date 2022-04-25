package springseller.filipey.services.impl;

import org.springframework.stereotype.Service;
import springseller.filipey.repositories.RequestRepository;
import springseller.filipey.services.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;


    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
}
