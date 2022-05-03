package springseller.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.webservice.domain.ProductRequest;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {
}
