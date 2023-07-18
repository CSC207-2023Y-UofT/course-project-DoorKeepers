package entities;

public class EntityException extends Exception {

    /**
     * Creates a new EntityException for reporting exceptions specific to the entities.
     */
    public EntityException(String message) {
        super(message);
    }
}
