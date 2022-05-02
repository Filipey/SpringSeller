package springseller.filipey.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springseller.filipey.domain.enums.RoleType;
import springseller.filipey.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        springseller.filipey.domain.User myUser = userRepository.findByLogin(username)
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
    public springseller.filipey.domain.User save(springseller.filipey.domain.User user) {

        String cryptoPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptoPassword);

        return userRepository.save(user);
    }
}
