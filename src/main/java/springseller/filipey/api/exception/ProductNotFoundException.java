package springseller.filipey.api.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        super("Product not found");
    }
}
