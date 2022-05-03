package springseller.webservice.api.exception;

public class ProductNotFoundException extends ObjectNotFoundException{

    public ProductNotFoundException() {
        super("Product not found");
    }
}
