package springseller.filipey.api.exception;

public class ClientNotFoundException extends ObjectNotFoundException{

    public ClientNotFoundException() {
        super("Client not found");
    }
}
