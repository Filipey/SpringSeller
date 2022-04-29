package springseller.filipey.api.exception;

public class ProductNotFoundException extends ObjectNotFoundException{

    public ProductNotFoundException() {
        super("Product not found");
    }
}
