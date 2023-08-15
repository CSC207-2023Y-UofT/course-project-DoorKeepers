package entities;

/**
 * An abstract class that contains the method needed to create and edit a MonthObject.
 */
public abstract class MonthObjectFactory {

    /**
     * Creates and returns a new MonthObject.
     * @param inputData a MonthObjectFactoryInputData object that holds the information needed to create a MonthObject
     * @return a new MonthObject
     */
    public abstract MonthObject createMonthObject(MonthObjectFactoryInputData inputData);

    /**
     * Edits and returns a MonthObject.
     * @param inputData a MonthObjectFactoryInputData object that holds the information needed to edit a MonthObject
     * @return the edited MonthObject
     */
    public abstract MonthObject editMonthObject(MonthObjectFactoryInputData inputData);
}