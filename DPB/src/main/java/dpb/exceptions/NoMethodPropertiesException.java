package dpb.exceptions;


public class NoMethodPropertiesException extends Exception {

    public NoMethodPropertiesException() {
        super("No method properties were found.");
    }

    public NoMethodPropertiesException(String message) {
        super(message);
    }

    public NoMethodPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMethodPropertiesException(Throwable cause) {
        super("No class properties were found.", cause);
    }
}
