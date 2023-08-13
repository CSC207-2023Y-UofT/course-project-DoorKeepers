package entities;

/**
 * An interface that objects belonging to the MonthlyStorage implement. Expense and Category implement this so that
 * they can be created by MonthObjectFactory classes. It contains the setName and getName methods that all MonthObject
 * classes should have.
 */
public interface MonthObject {

    /**
     * Sets the name of this MonthObject.
     * @param name the new name that this MonthObject will have
     */
    void setName(String name);

    /**
     * Gets the name of this MonthObject.
     * @return the name of this MonthObject
     */
    String getName();
}