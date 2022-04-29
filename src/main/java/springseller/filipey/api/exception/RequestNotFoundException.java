package springseller.filipey.api.exception;

public class RequestNotFoundException extends ObjectNotFoundException {

    public RequestNotFoundException() {
        super("Request not found");
    }
}
