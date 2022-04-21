package springseller.filipey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class SpringConfiguration {

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("Running Development config");
        };
    }
}
