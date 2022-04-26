package springseller.filipey.api.controllers;

import org.springframework.web.bind.annotation.*;
import springseller.filipey.api.dto.RequestDTO;
import springseller.filipey.services.RequestService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Long save( @RequestBody RequestDTO dto ) {
        return requestService.save(dto).getId();
    }
}
