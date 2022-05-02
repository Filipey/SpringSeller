package springseller.filipey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springseller.filipey.domain.enums.RoleType;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("filipe")
                .password(passwordEncoder().encode("123"))
                .roles(RoleType.ADMINISTRATOR.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/clients/**")
                        .hasAnyRole(RoleType.USER.name(), RoleType.ADMINISTRATOR.name())
                    .antMatchers("/api/v1/requests/**")
                        .hasAnyRole(RoleType.USER.name(), RoleType.ADMINISTRATOR.name())
                    .antMatchers("/api/v1/products/**")
                        .hasAnyRole(RoleType.ADMINISTRATOR.name())
                .and()
                    .httpBasic();
    }
}
