package springseller.filipey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springseller.filipey.domain.entity.Client;
import springseller.filipey.domain.repository.ClientRepository;

import java.util.List;

@SpringBootApplication
public class SpringSellerApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clients) {
        return args -> {
            System.out.println("Saving clients");
            clients.save(new Client("Filipe"));
            clients.save(new Client("Paulo"));

            List<Client> all = clients.findAll();

            all.forEach(System.out::println);

            System.out.println("Updating clients");
            all.forEach(c -> {
                c.setName(c.getName() + " updated");
                clients.save(c);
            });

            all.forEach(System.out::println);

            System.out.println("Getting a client like 'Pau'");
            clients.findByNameLike("Pau").forEach(System.out::println);

            System.out.println("Deleting clients");
            clients.findAll().forEach(c -> {
                clients.delete(c);
            });

            all = clients.findAll();
            if (all.isEmpty()) {
                System.out.println("Have no clients");
            } else {
                all.forEach(System.out::println);
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSellerApplication.class, args);
    }
}
