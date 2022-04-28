package springseller.filipey.api.exception;

public class RequestNotFoundException extends RuntimeException {

    public RequestNotFoundException() {
        super("Request not found");
    }
}
