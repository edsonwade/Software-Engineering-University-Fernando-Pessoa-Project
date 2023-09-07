package ufp.esof.project.exception;

public class CustomJsonSerializationException extends RuntimeException {

    private static final long serialVersionUID = -3128964819025427105L;

    public CustomJsonSerializationException(String message) {
        super(message);
    }

    public CustomJsonSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
