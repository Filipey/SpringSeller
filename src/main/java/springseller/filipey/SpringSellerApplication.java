package springseller.filipey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springseller.filipey.domain.Client;
import springseller.filipey.domain.Request;
import springseller.filipey.repositories.ClientRepository;
import springseller.filipey.repositories.RequestRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSellerApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientRepository clients,
            @Autowired RequestRepository requests) {
        return args -> {
            System.out.println("Saving clients");
            Client filipe = new Client("Filipe");
            clients.save(filipe);

            Request request = new Request();
            request.setClient(filipe);
            request.setRequestDate(LocalDate.now());
            request.setAmount(BigDecimal.valueOf(100));

            requests.save(request);

            requests.findByClient(filipe).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSellerApplication.class, args);
    }
}
