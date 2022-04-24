package springseller.filipey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import springseller.filipey.domain.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameLike(String name);

    @Modifying
    void deleteByName(String name);

    boolean existsByName(String name);
}
