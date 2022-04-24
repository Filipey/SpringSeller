package springseller.filipey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.filipey.domain.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameLike(String name);

    boolean existsByName(String name);
}
