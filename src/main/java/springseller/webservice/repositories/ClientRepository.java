package springseller.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springseller.webservice.domain.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameLike(String name);

    @Modifying
    void deleteByName(String name);

    boolean existsByName(String name);

    @Query(" select c from Client c left join fetch c.requests where c.id = :id")
    Client findClientFetchRequests(@Param("id") Long id);

}
