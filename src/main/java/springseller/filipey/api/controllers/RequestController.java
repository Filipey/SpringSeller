package springseller.filipey.api.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springseller.filipey.api.dto.RequestDTO;
import springseller.filipey.api.dto.RequestInfoDTO;
import springseller.filipey.api.dto.RequestStatusUpdateDTO;
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

    @GetMapping("{id}")
    public RequestInfoDTO getById( @PathVariable Long id ) {
        return requestService.getById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus( @PathVariable Long id, @RequestBody RequestStatusUpdateDTO dto ) {
        requestService.updateStatus(id, dto);
    }
}
