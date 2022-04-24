package springseller.filipey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.filipey.domain.Client;
import springseller.filipey.domain.Request;

import java.util.Set;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Set<Request> findByClient(Client client);

}
