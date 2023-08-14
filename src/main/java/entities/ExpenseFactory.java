package entities;

/**
 * A class that creates an Expense. It extends the abstract class MonthObjectFactory.
 */
public class ExpenseFactory extends MonthObjectFactory {

    /**
     * Creates and returns a new Expense.
     * @param inputData an ExpenseCreatorInputData object that is cast to MonthObjectFactoryInputData.
     *                  It holds the information needed to create an Expense.
     * @return the newly created Expense
     */
    @Override
    public MonthObject createMonthObject(MonthObjectFactoryInputData inputData){
        ExpenseCreatorInputData expenseCreatorInputData = (ExpenseCreatorInputData) inputData;
        return new Expense(expenseCreatorInputData.getName(), expenseCreatorInputData.getCategory(),
                expenseCreatorInputData.getValue());
    }

    /**
     * Edits and returns an Expense.
     * @param inputData an ExpenseEditorInputData object that is cast to MonthObjectFactoryInputData.
     *                  It holds the information needed to edit an Expense.
     * @return the newly edited Expense
     */
    @Override
    public MonthObject editMonthObject(MonthObjectFactoryInputData inputData) {
        ExpenseEditorInputData expenseEditorInputData = (ExpenseEditorInputData) inputData;
        Expense expense = expenseEditorInputData.getExpense();
        expense.setName(expenseEditorInputData.getName());
        expense.setCategory(expenseEditorInputData.getCategory());
        expense.setValue(expenseEditorInputData.getValue());
        return expense;
    }
}