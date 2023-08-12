package entities;

/**
 * A class that holds the input data for use by the ExpenseCreator. It extends MonthObjectCreatorInputData, so that it
 * can be used in place of this type. It includes the name, value, and category that the new Expense will have.
 */
public class ExpenseCreatorInputData extends MonthObjectCreatorInputData {

    private final String name;
    private final Category category;
    private final double value;

    /**
     * Creates a new instance of ExpenseCreatorInputData.
     * @param name the name of the expense
     * @param category the category of the expense
     * @param value the value of the expense
     */
    public ExpenseCreatorInputData(String name, Category category, double value){
        this.name = name;
        this.category = category;
        this.value = value;
    }

    /**
     * Gets the name of this ExpenseCreatorInputData.
     * @return the name of this ExpenseCreatorInputData
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category of this ExpenseCreatorInputData.
     * @return the category of this ExpenseCreatorInputData
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the value of this ExpenseCreatorInputData.
     * @return the value of this ExpenseCreatorInputData
     */
    public double getValue() {
        return value;
    }
}