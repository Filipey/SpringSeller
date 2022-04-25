package springseller.filipey.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springseller.filipey.domain.Product;
import springseller.filipey.repositories.ProductsRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/{id}")
    public Product getProductById( @PathVariable Long id ) {
        return productsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product insert( @RequestBody Product product ) {
        return productsRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Long id ) {
        productsRepository
                .findById(id)
                .map(product -> {
                    productsRepository.delete(product);
                    return product;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Long id, @RequestBody Product product ) {
        productsRepository
                .findById(id)
                .map(existingProduct -> {
                    product.setId(existingProduct.getId());
                    productsRepository.save(product);
                    return product;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping
    public List<Product> find(Product filteredProduct) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filteredProduct, matcher);

        if (productsRepository.findAll(example).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return productsRepository.findAll(example);
    }
}
