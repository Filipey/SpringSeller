package springseller.filipey.services;

import springseller.filipey.domain.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Long id);

    public Product insert(Product product);

    public void delete(Long id);

    public void update(Long id, Product product);

    public List<Product> find(Product filteredProduct);
}
