package springseller.webservice.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springseller.webservice.domain.User;
import springseller.webservice.services.impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save( @RequestBody @Valid User user ) {
        return  userService.save(user);
    }
}
