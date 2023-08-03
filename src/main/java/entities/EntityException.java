package entities;

/**
 * An exception thrown in response to an error creating one of the base entities, specified by message.
 */
public class EntityException extends Exception {

    /**
     * Creates a new EntityException with given message.
     * @param message a String detailing the cause of the exception
     */
    public EntityException(String message) {
        super(message);
    }
}
