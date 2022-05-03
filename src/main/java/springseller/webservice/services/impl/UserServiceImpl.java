package springseller.webservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springseller.webservice.api.dto.UserCredentialsDTO;
import springseller.webservice.api.dto.UserTokenDTO;
import springseller.webservice.api.exception.InvalidPasswordException;
import springseller.webservice.api.security.jwt.JwtService;
import springseller.webservice.domain.enums.RoleType;
import springseller.webservice.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        springseller.webservice.domain.User myUser = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        String[] roles = myUser.isAdmin() ?
                new String[] {RoleType.ADMINISTRATOR.name(), RoleType.USER.name()} :
                new String[] {RoleType.USER.name()};

        return User
                .builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(roles)
                .build();
    }

    @Transactional
    public springseller.webservice.domain.User save(springseller.webservice.domain.User user) {

        String cryptoPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptoPassword);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDetails authenticate(springseller.webservice.domain.User user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());

        boolean isPasswordsValid =  passwordEncoder.matches(user.getPassword(), userDetails.getPassword());

        if (isPasswordsValid) {
            return userDetails;
        }

        throw new InvalidPasswordException();
    }

    public UserTokenDTO insert(UserCredentialsDTO dto) {
        try {
            springseller.webservice.domain.User user = springseller.webservice.domain.User.builder()
                    .login(dto.getLogin())
                    .password(dto.getPassword())
                    .build();

            UserDetails authenticatedUser = authenticate(user);

            String token = jwtService.generateToken(user);

            return new UserTokenDTO(authenticatedUser.getUsername(), token);

        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
