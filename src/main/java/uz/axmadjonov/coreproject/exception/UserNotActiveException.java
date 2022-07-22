package uz.axmadjonov.coreproject.exception;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(String message) {
        super(message);
    }

    public UserNotActiveException() {
        super("User not active!");
    }
}
