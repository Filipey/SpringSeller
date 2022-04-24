package springseller.filipey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.filipey.domain.ProductRequest;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {
}
