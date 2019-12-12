package Exception;

public class DeckHasNoCardException extends RuntimeException {
    public DeckHasNoCardException(String message) {
        super(message);
    }
}
