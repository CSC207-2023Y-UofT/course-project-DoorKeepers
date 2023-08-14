package entities;

/**
 * A class that holds the input data for use by the ExpenseFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the name, value, and category that the new Expense will have.
 */
public class ExpenseCreatorInputData extends MonthObjectFactoryInputData {

    private String name;
    private Category category;
    private double value;

    /**
     * Sets the name of this ExpenseCreatorInputData.
     * @param name the name that the Expense will have
     */
    @Override
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the Category of this ExpenseCreatorInputData.
     * @param newCategory the Category that the Expense will have
     */
    public void setCategory(Category newCategory){
        this.category = newCategory;
    }

    /**
     * Sets the value of this ExpenseCreatorInputData.
     * @param newValue the value that the Expense will have
     */
    public void setValue(double newValue){
        this.value = newValue;
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