package entities;

/**
 * A class that creates an Expense. It extends the abstract class MonthObjectCreator.
 */
public class ExpenseCreator extends MonthObjectCreator {

    /**
     * Creates and returns a new Expense.
     * @param inputData an ExpenseCreatorInputData object that is cast to MonthObjectCreatorInputData.
     *                  It holds the information needed to create an Expense.
     * @return the newly created Expense
     */
    @Override
    public MonthObject createMonthObject(MonthObjectCreatorInputData inputData){
        ExpenseCreatorInputData expenseCreatorInputData = (ExpenseCreatorInputData) inputData;
        return new Expense(expenseCreatorInputData.getName(), expenseCreatorInputData.getCategory(),
                expenseCreatorInputData.getValue());
    }
}