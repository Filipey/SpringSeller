package springseller.filipey.api.exception;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException() {
        super("Client not found");
    }
}
