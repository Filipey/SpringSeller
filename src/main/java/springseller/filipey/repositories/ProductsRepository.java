package springseller.filipey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springseller.filipey.domain.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
