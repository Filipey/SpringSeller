package springseller.webservice.services;

import springseller.webservice.domain.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    Product insert(Product product);

    void delete(Long id);

    void update(Long id, Product product);

    List<Product> find(Product filteredProduct);
}
