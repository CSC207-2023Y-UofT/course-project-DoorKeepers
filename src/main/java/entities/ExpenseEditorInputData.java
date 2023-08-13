package entities;

/**
 * A class that holds the input data for use by the ExpenseFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the new name, new value, new category, and Expense object to edit.
 */
public class ExpenseEditorInputData extends MonthObjectFactoryInputData {

    private String name;
    private Category category;
    private double value;
    private Expense expense;

    /**
     * Sets the name of this ExpenseEditorInputData.
     * @param name the new name that the edited Expense will have
     */
    @Override
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the Category of this ExpenseEditorInputData.
     * @param category the new Category that the edited Expense will have
     */
    public void setCategory(Category category){
        this.category = category;
    }

    /**
     * Sets the value of this ExpenseEditorInputData.
     * @param value the new value that the edited Expense will have
     */
    public void setValue(double value){
        this.value = value;
    }

    /**
     * Sets the Expense object of this ExpenseEditorInputData.
     * @param expense the Expense to edit
     */
    public void setExpense(Expense expense){
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