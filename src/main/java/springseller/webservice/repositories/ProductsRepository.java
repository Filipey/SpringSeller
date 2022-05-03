package springseller.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.webservice.domain.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
