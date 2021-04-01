package exception;

    public class NotExistItemException extends RuntimeException {

    public NotExistItemException() {

    }

    public NotExistItemException(String message) {
        super(message);
    }

    public NotExistItemException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotExistItemException(Throwable throwable) {
        super(throwable);
    }
}
