package springseller.filipey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.filipey.domain.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
