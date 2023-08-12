package entities;

/**
 * An interface that objects belonging to the MonthlyStorage implement. Expense and Category implement this so that
 * they can be created by MonthObjectFactory classes. It contains the setName and getName methods that all MonthObject
 * classes should have.
 */
public interface MonthObject {
    void setName(String name);
    String getName();
}