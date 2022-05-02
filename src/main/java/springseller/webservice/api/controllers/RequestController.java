package springseller.webservice.api.controllers;

import org.springframework.web.bind.annotation.*;
import springseller.webservice.api.dto.RequestDTO;
import springseller.webservice.api.dto.RequestInfoDTO;
import springseller.webservice.api.dto.RequestStatusUpdateDTO;
import springseller.webservice.services.RequestService;

import javax.validation.Valid;

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
    public Long save( @RequestBody @Valid RequestDTO dto ) {
        return requestService.save(dto).getId();
    }

    @GetMapping("{id}")
    public RequestInfoDTO getById( @PathVariable Long id ) {
        return requestService.getById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus( @PathVariable Long id, @RequestBody @Valid RequestStatusUpdateDTO dto ) {
        requestService.updateStatus(id, dto);
    }
}
