package dpb.exceptions;

public class NoClassPropertiesException extends Exception {

    public NoClassPropertiesException() {
        super("No class properties were found.");
    }

    public NoClassPropertiesException(String message) {
        super(message);
    }

    public NoClassPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoClassPropertiesException(Throwable cause) {
        super("No class properties were found.", cause);
    }
}
