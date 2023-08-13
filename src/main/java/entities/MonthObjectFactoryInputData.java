package entities;

/**
 * An abstract class that other concrete CreatorInputData and EditorInputData classes extend. It holds the input data
 * for use by any concrete MonthObjectFactory class.
 * Note: This class is not a part of the Factory design pattern. Since CategoryFactory and ExpenseFactory need different
 * parameters for addMonthObject and editMonthObject, the abstract MonthObjectFactory needs a way to declare a parameter
 * type for these methods. Thus, I created the MonthObjectFactoryInputData and made both methods take this parameter, so
 * that each version of addMonthObject and editMonthObject in CategoryFactory and ExpenseFactory can take in a different
 * set of input data objects that are child classes of MonthObjectFactoryInputData.
 */
abstract class MonthObjectFactoryInputData {

    /**
     * Sets the name of this MonthObject.
     * @param name the new name that this MonthObject will have
     */
    abstract void setName(String name);

    /**
     * Gets the name of this MonthObject.
     * @return the name of this MonthObject
     */
    abstract String getName();
}