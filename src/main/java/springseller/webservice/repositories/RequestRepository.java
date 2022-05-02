package springseller.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springseller.webservice.domain.Client;
import springseller.webservice.domain.Request;

import java.util.Optional;
import java.util.Set;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Set<Request> findByClient(Client client);

    @Query("select r from Request r left join fetch r.products where r.id = :id")
    Optional<Request> findByIdFetchProducts(@Param("id") Long id);

}
