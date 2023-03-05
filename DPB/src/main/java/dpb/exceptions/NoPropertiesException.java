package dpb.exceptions;

public class NoPropertiesException extends Exception {

    private static final long serialVersionUID = 1L;

	public NoPropertiesException() {
        super("No properties were found.");
    }

    public NoPropertiesException(String message) {
        super(message);
    }

    public NoPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPropertiesException(Throwable cause) {
        super("No properties were found.", cause);
    }
}
