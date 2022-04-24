package springseller.filipey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.filipey.domain.entity.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameLike(String name);
}
