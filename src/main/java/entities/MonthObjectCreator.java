package entities;

/**
 * An abstract class that contains the method needed to create a MonthObject.
 */
abstract class MonthObjectCreator {

    /**
     * Creates and returns a new MonthObject.
     * @param inputData a MonthObjectCreatorInputData object that holds the information needed to create a MonthObject
     * @return a new MonthObject
     */
    public abstract MonthObject createMonthObject(MonthObjectCreatorInputData inputData);
}