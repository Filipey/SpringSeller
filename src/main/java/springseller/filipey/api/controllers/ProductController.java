package springseller.filipey.api.controllers;

import org.springframework.web.bind.annotation.*;
import springseller.filipey.domain.Product;
import springseller.filipey.services.impl.ProductServiceImpl;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProductById( @PathVariable Long id ) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product insert( @RequestBody @Valid Product product ) {
        return productService.insert(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete( @PathVariable Long id ) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Long id, @RequestBody @Valid Product product ) {
        productService.update(id, product);
    }

    @GetMapping
    public List<Product> find(Product filteredProduct) {
       return productService.find(filteredProduct);
    }
}
