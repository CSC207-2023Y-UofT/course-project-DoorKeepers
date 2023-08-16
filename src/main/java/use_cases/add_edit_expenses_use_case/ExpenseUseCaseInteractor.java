package use_cases.add_edit_expenses_use_case;

import entities.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ExpenseUseCaseInteractor implements ExpenseInputBoundary {
    private final ExpenseOutputBoundary expenseOutputBoundary;
    private final MonthObjectFactory expenseFactory;
    private MonthlyStorage month;

    /**
     * Constructs ExpenseUseCaseInteractor. An ExpenseFactory is constructed.
     *
     * @param expenseP presenter that is related to the use case.
     */
    public ExpenseUseCaseInteractor(ExpenseOutputBoundary expenseP) {
        this.expenseOutputBoundary = expenseP;
        this.expenseFactory = new ExpenseFactory();
    }

    /**
     * Helper method returns a double to check if the input Object is a valid double.
     *
     * @param value a user input
     * @return double converted from value
     */
    public static double toDouble(Object value) throws NumberFormatException {
        return Double.parseDouble(value.toString());
    }

    /**
     * Overrides method in ExpenseInputBoundary.
     * Attempts to add an expense with information from ExpenseInputData and returns a ExpenseOutputData
     * indicating whether fail/success after execution.
     * ExpenseFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below
     *
     * @param expenseInputDataAdd ExpenseInputData required for adding a new expense to the designated monthID MonthlyStorage Object.
     * @return ExpenseOutputData String message indicating success/fail add attempt.
     * @throws EntityException thrown when the new expense input is invalid.
     */
    @Override
    public ExpenseOutputData addExpenseInMonth(ExpenseInputData expenseInputDataAdd) throws EntityException {
        SessionStorage session = expenseInputDataAdd.getSession();
        this.month = session.getMonthlyData(expenseInputDataAdd.getMonthID());
        try {
            double valueDouble = toDouble(expenseInputDataAdd.getValue());

            if (valueDouble < 0) {
                //Expense value less than 0 fail: When a user tries to add the expense value with a negative number.
                ExpenseOutputData expenseOutputDataFailAdd = new ExpenseOutputData("Expense value can't be less than $0. Please try again!");
                return expenseOutputBoundary.fail(expenseOutputDataFailAdd);
            }

            if (isExpenseNameInList(month.getExpenseData(), expenseInputDataAdd.getName())) {
                //Repeated name in month
                ExpenseOutputData expenseOutputDataFailEdit = new ExpenseOutputData("There is already a expense with this new name " +
                        "in this month.");
                return expenseOutputBoundary.fail(expenseOutputDataFailEdit);
            }

            if (expenseInputDataAdd.getIsRecurringExpense()) {
                Expense newRecurExpense = (Expense) expenseFactory.createMonthObject(setExpenseCreatorFactoryInputData(expenseInputDataAdd));
                session.addRecurExpense(newRecurExpense);
                month.addExpense(newRecurExpense);
                ExpenseOutputData expenseOutputDataSuccessAdd = new ExpenseOutputData("You have created a new recurring expense!");
                return expenseOutputBoundary.success(expenseOutputDataSuccessAdd);
            } else {
                Expense newExpense = (Expense) expenseFactory.createMonthObject(setExpenseCreatorFactoryInputData(expenseInputDataAdd));
                month.addExpense(newExpense);
                ExpenseOutputData expenseOutputDataSuccessAdd = new ExpenseOutputData("You have added a new expense!");
                return expenseOutputBoundary.success(expenseOutputDataSuccessAdd);
            }

        } catch (NumberFormatException e) {
            // NumberFormatException|NullPointerException fail: User tries to edit Expense value to an invalid number.
            ExpenseOutputData expenseOutputDataFailAdd = new ExpenseOutputData("Expense value needs to be a number. Please try again!");
            return expenseOutputBoundary.fail(expenseOutputDataFailAdd);
        } catch (EntityException e) {
            // EntityException fail: User tries to add an invalid Expense name but failed. (See entities/EntityException.java)
            ExpenseOutputData expenseOutputDataFailAdd = new ExpenseOutputData("There is already a expense with this new name in this month.");
            return expenseOutputBoundary.fail(expenseOutputDataFailAdd);
        }
    }

    /**
     * Attempts to edit an expense with information from ExpenseInputData and returns a ExpenseOutputData indicating whether fail/success after execution.
     * ExpenseFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below
     *
     * @param expenseInputDataEdit ExpenseInputData required for editing a new expense to the designated monthID MonthlyStorage Object.
     * @return ExpenseOutputData String message indicating success/fail add attempt.
     * @throws EntityException (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     *                         it will be caught at views/add_edit_expense_views/EditExpenseView.java).
     */
    @Override
    public ExpenseOutputData editExpenseInMonth(ExpenseInputData expenseInputDataEdit) throws EntityException {
        try {
            SessionStorage session = expenseInputDataEdit.getSession();
            this.month = session.getMonthlyData(expenseInputDataEdit.getMonthID());
            ArrayList<Expense> monthExpenseList = month.getExpenseData();
            double valueDouble = toDouble(expenseInputDataEdit.getValue());

            if (valueDouble < 0) {
                //Expense value less than 0 fail: When a user tries to edit the expense value with a negative number.
                ExpenseOutputData expenseOutputDataFailEdit = new ExpenseOutputData("Expense value can't be less than $0. Please try again!");
                return expenseOutputBoundary.fail(expenseOutputDataFailEdit);
            }

            if (!Objects.equals(expenseInputDataEdit.getName(), expenseInputDataEdit.getOldExpense())) {
                if (isExpenseNameInList(monthExpenseList, expenseInputDataEdit.getName())) {
                    // Repeated name fail: When a user tries to edit the expense name to an existing expense in month.
                    ExpenseOutputData expenseOutputDataFailEdit = new ExpenseOutputData("There is already a expense with this new name " +
                            "in this month.");
                    return expenseOutputBoundary.fail(expenseOutputDataFailEdit);
                }
            }

            if (changeInRecurringInfo(expenseInputDataEdit)) {
                if (expenseInputDataEdit.getIsRecurringExpense()) {
                    //If this edited expense is newly recurring, first ExpenseFactory modifies and edits this expense
                    // according to expenseID, then session.addRecurExpense() updates session.getRecurData().
                    // Consequently, the user is shown with a corresponding message.
                    Expense newRecurExpense = (Expense) expenseFactory.editMonthObject(setExpenseEditorFactoryInputData(expenseInputDataEdit));
                    session.addRecurExpense(newRecurExpense);
                    ExpenseOutputData expenseOutputDataSuccessEditRecurring = new ExpenseOutputData("You have updated all changes " +
                            "of this recurring expense!");
                    return expenseOutputBoundary.success(expenseOutputDataSuccessEditRecurring);
                } else {
                    // If this edited expense is no longer recurring, first ExpenseFactory modifies and edits this expense
                    // according to expenseID, then session.deleteRecurExpense() deletes this expense from session.getRecurData().
                    //(Note the targeted expense is now modified to the new name expenseID.getName() and not the old name.)
                    // Consequently, the user shown with a corresponding message.
                    expenseFactory.editMonthObject(setExpenseEditorFactoryInputData(expenseInputDataEdit));
                    session.deleteRecurExpense(expenseInputDataEdit.getName());
                    ExpenseOutputData expenseOutputDataSuccessEditRecurring = new ExpenseOutputData("You have updated all changes " +
                            "of this expense and it is no longer a recurring expense!");
                    return expenseOutputBoundary.success(expenseOutputDataSuccessEditRecurring);
                }
            } else {
                expenseFactory.editMonthObject(setExpenseEditorFactoryInputData(expenseInputDataEdit));
            }

            //Success edit to expense
            ExpenseOutputData expenseOutputDataSuccessEdit = new ExpenseOutputData("You have edited an expense!");
            return expenseOutputBoundary.success(expenseOutputDataSuccessEdit);

        } catch (NoSuchElementException e) {
            //NoSuchElementException fail: User tries to edit an expense that does not exist.
            ExpenseOutputData expenseOutputDataFailEdit = new ExpenseOutputData("There is no such expense in the current month. " +
                    "Please add a new expense or select existing expense!");
            return expenseOutputBoundary.fail(expenseOutputDataFailEdit);
        } catch (NumberFormatException e) {
            // NumberFormatException|NullPointerException fail: User tries to edit Expense value to an invalid number.
            ExpenseOutputData expenseOutputDataFailEdit = new ExpenseOutputData("Expense value needs to be a number. Please try again!");
            return expenseOutputBoundary.fail(expenseOutputDataFailEdit);
        }
    }

    /**
     * Finds Expense with String name from a provided list of Expenses.
     *
     * @param expenseData An ArrayList of expenses.
     * @param name        Expense name.
     * @return Expense with given String name.
     * @throws NoSuchElementException thrown when couldn't find Expense with String name.
     */
    public Expense findExpense(ArrayList<Expense> expenseData, String name) throws NoSuchElementException {
        if (expenseData != null) {
            for (Expense expense : expenseData) {
                if (Objects.equals(expense.getName(), name)) {
                    return expense;
                }
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Finds Category with String name from a provided list of Categories.
     *
     * @param monthCategoryData An ArrayList of categories.
     * @param name              Category name.
     * @return Category with given String name.
     * @throws NoSuchElementException thrown when couldn't find Category with String name.
     */
    public Category findCategory(ArrayList<Category> monthCategoryData, String name) throws NoSuchElementException {
        for (Category category : monthCategoryData) {
            if (Objects.equals(category.getName(), name)) {
                return category;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Checks if a given Expense arraylist contains a name that is the same as the Expense name input.
     *
     * @param expenseList ArrayList<Expense> containing a list of expenses
     * @return boolean checks if same name exists
     */
    private boolean isExpenseNameInList(ArrayList<Expense> expenseList, String name) {
        for (Expense expense1 : expenseList) {
            if (expense1.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether there's a difference in the recurring status of the old expense and the edited expense.
     * This could mean that the expense wasn't recurring and now the user is trying to make it recurring,
     * or vice versa.
     *
     * @return a boolean describing whether there was a change in the recurring status of the expense
     */
    private boolean changeInRecurringInfo(ExpenseInputData expenseInputData) {
        boolean isOldExpenseRecurring;
        try {
            findExpense(expenseInputData.getSession().getRecurData(), expenseInputData.getOldExpense());
            isOldExpenseRecurring = true;
        } catch (NoSuchElementException e) {
            isOldExpenseRecurring = false;
        }
        return isOldExpenseRecurring != expenseInputData.getIsRecurringExpense();
    }

    /**
     * Sets the information needed to create an ExpenseCreatorInputData to call createMonthObject method in ExpenseFactory
     *
     * @return ExpenseCreatorInputData MonthObjectFactoryInputData Object specifically used in ExpenseFactory
     * for the createMonthObject method.
     */
    private MonthObjectFactoryInputData setExpenseCreatorFactoryInputData(ExpenseInputData expenseInputDataAdd) {
        ExpenseCreatorInputData expenseCreatorInputData = new ExpenseCreatorInputData();
        expenseCreatorInputData.setName(expenseInputDataAdd.getName());
        expenseCreatorInputData.setValue(toDouble(expenseInputDataAdd.getValue()));
        expenseCreatorInputData.setCategory(findCategory(month.getCategoryData(), expenseInputDataAdd.getCategory()));
        return expenseCreatorInputData;
    }

    /**
     * Sets the information needed to edit an ExpenseEditorInputData to call editMonthObject method in ExpenseFactory
     *
     * @return ExpenseEditorInputData MonthObjectFactoryInputData Object specifically used in ExpenseFactory
     * for the editMonthObject method.
     */
    private MonthObjectFactoryInputData setExpenseEditorFactoryInputData(ExpenseInputData expenseInputDataEdit) {
        ExpenseEditorInputData expenseEditorInputData = new ExpenseEditorInputData();
        expenseEditorInputData.setName(expenseInputDataEdit.getName());
        expenseEditorInputData.setExpense(findExpense(month.getExpenseData(), expenseInputDataEdit.getOldExpense()));
        expenseEditorInputData.setCategory(findCategory(month.getCategoryData(), expenseInputDataEdit.getCategory()));
        expenseEditorInputData.setValue(toDouble(expenseInputDataEdit.getValue()));
        return expenseEditorInputData;
    }
}