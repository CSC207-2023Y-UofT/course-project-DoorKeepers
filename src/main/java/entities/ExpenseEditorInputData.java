package entities;

/**
 * A class that holds the input data for use by the ExpenseFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the new name, new value, new category, and Expense object to edit.
 */
public class ExpenseEditorInputData extends MonthObjectFactoryInputData {

    private final String name;
    private final Category category;
    private final double value;
    private final Expense expense;

    /**
     * Creates a new instance of ExpenseEditorInputData.
     * @param name the name of the expense
     * @param category the category of the expense
     * @param value the value of the expense
     * @param expense the Expense object
     */
    public ExpenseEditorInputData(String name, Category category, double value, Expense expense){
        this.name = name;
        this.category = category;
        this.value = value;
        this.expense = expense;
    }

    /**
     * Gets the name of this ExpenseEditorInputData.
     * @return the name of this ExpenseEditorInputData
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category of this ExpenseEditorInputData.
     * @return the category of this ExpenseEditorInputData
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the value of this ExpenseEditorInputData.
     * @return the value of this ExpenseEditorInputData
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the Expense object of this ExpenseEditorInputData.
     * @return the Expense object of this ExpenseEditorInputData
     */
    public Expense getExpense() {
        return expense;
    }
}