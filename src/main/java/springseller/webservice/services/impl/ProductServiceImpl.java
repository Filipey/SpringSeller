package springseller.webservice.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springseller.webservice.api.exception.ProductNotFoundException;
import springseller.webservice.domain.Product;
import springseller.webservice.repositories.ProductsRepository;
import springseller.webservice.services.ProductService;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException());
    }

    @Override
    public Product insert(Product product) {
        return productsRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productsRepository
                .findById(id)
                .map(product -> {
                    productsRepository.delete(product);
                    return product;
                }).orElseThrow(
                        () -> new ProductNotFoundException());
    }

    @Override
    public void update(Long id, Product product) {
        productsRepository
                .findById(id)
                .map(existingProduct -> {
                    product.setId(existingProduct.getId());
                    productsRepository.save(product);
                    return product;
                }).orElseThrow(
                        () -> new ProductNotFoundException());
    }

    @Override
    public List<Product> find(Product filteredProduct) {
        ExampleMatcher matcher =
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filteredProduct, matcher);

        if (productsRepository.findAll(example).isEmpty()) {
            throw new ProductNotFoundException();
        }

        return productsRepository.findAll(example);
    }
}
