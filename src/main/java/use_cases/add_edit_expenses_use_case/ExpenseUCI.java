package use_cases.add_edit_expenses_use_case;

import entities.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ExpenseUCI implements ExpenseIB {
    private final ExpenseOB expenseOB;
    private final MonthObjectFactory expenseFactory;
    private MonthlyStorage month;

    /**
     * Constructs ExpenseUCI. An ExpenseFactory is constructed.
     *
     * @param expenseP presenter that is related to the use case.
     */
    public ExpenseUCI(ExpenseOB expenseP) {
        this.expenseOB = expenseP;
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
     * Overrides method in ExpenseIB.
     * Attempts to add an expense with information from ExpenseID and returns a ExpenseOD
     * indicating whether fail/success after execution.
     * ExpenseFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below
     *
     * @param expenseIDAdd ExpenseID required for adding a new expense to the designated monthID MonthlyStorage Object.
     * @return ExpenseOD String message indicating success/fail add attempt.
     */
    @Override
    public ExpenseOD addExpenseInMonth(ExpenseID expenseIDAdd) {
        try {
            SessionStorage session = expenseIDAdd.getSession();
            this.month = session.getMonthlyData(expenseIDAdd.getMonthID());
            double valueDouble = toDouble(expenseIDAdd.getValue());

            if (valueDouble < 0) {
                //Expense value less than 0 fail: When a user tries to add the expense value with a negative number.
                ExpenseOD expenseODFailAdd = new ExpenseOD("Expense value can't be less than $0. Please try again!");
                return expenseOB.fail(expenseODFailAdd);
            }

            if (isExpenseNameInList(month.getExpenseData(), expenseIDAdd.getName())) {
                //Repeated name in month
                ExpenseOD expenseODFailEdit = new ExpenseOD("There is already a expense with this new name " +
                        "in this month.");
                return expenseOB.fail(expenseODFailEdit);
            }

            if (expenseIDAdd.getIsRecurringExpense()) {
                Expense newRecurExpense = (Expense) expenseFactory.createMonthObject(setExpenseCreatorFactoryInputData(expenseIDAdd));
                session.addRecurExpense(newRecurExpense);
                month.addExpense(newRecurExpense);
                ExpenseOD expenseODSuccessAdd = new ExpenseOD("You have created a new recurring expense!");
                return expenseOB.success(expenseODSuccessAdd);
            } else {
                Expense newExpense = (Expense) expenseFactory.createMonthObject(setExpenseCreatorFactoryInputData(expenseIDAdd));
                month.addExpense(newExpense);
                ExpenseOD expenseODSuccessAdd = new ExpenseOD("You have added a new expense!");
                return expenseOB.success(expenseODSuccessAdd);
            }

        } catch (NumberFormatException e) {
            // NumberFormatException|NullPointerException fail: User tries to edit Expense value to an invalid number.
            ExpenseOD expenseODFailAdd = new ExpenseOD("Expense value needs to be a number. Please try again!");
            return expenseOB.fail(expenseODFailAdd);
        } catch (EntityException e) {
            // EntityException fail: User tries to add an invalid Expense name but failed. (See entities/EntityException.java)
            ExpenseOD expenseODFailAdd = new ExpenseOD("There is already a expense with this new name in this month.");
            return expenseOB.fail(expenseODFailAdd);
        }
    }

    /**
     * Attempts to edit an expense with information from ExpenseID and returns a ExpenseOD indicating whether fail/success after execution.
     * ExpenseFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below
     *
     * @param expenseIDEdit ExpenseID required for editing a new expense to the designated monthID MonthlyStorage Object.
     * @return ExpenseOD String message indicating success/fail add attempt.
     * @throws EntityException (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     *                         it will be caught at views/add_edit_expense_views/EditExpenseV.java).
     */
    @Override
    public ExpenseOD editExpenseInMonth(ExpenseID expenseIDEdit) throws EntityException {
        try {
            SessionStorage session = expenseIDEdit.getSession();
            this.month = session.getMonthlyData(expenseIDEdit.getMonthID());
            ArrayList<Expense> monthExpenseList = month.getExpenseData();
            double valueDouble = toDouble(expenseIDEdit.getValue());

            if (valueDouble < 0) {
                //Expense value less than 0 fail: When a user tries to edit the expense value with a negative number.
                ExpenseOD expenseODFailEdit = new ExpenseOD("Expense value can't be less than $0. Please try again!");
                return expenseOB.fail(expenseODFailEdit);
            }

            if (!Objects.equals(expenseIDEdit.getName(), expenseIDEdit.getOldExpense())) {
                if (isExpenseNameInList(monthExpenseList, expenseIDEdit.getName())) {
                    // Repeated name fail: When a user tries to edit the expense name to an existing expense in month.
                    ExpenseOD expenseODFailEdit = new ExpenseOD("There is already a expense with this new name " +
                            "in this month.");
                    return expenseOB.fail(expenseODFailEdit);
                }
            }

            if (changeInRecurringInfo(expenseIDEdit)) {
                if (expenseIDEdit.getIsRecurringExpense()) {
                    //If this edited expense is newly recurring, first ExpenseFactory modifies and edits this expense
                    // according to expenseID, then session.addRecurExpense() updates session.getRecurData().
                    // Consequently, the user is shown with a corresponding message.
                    Expense newRecurExpense = (Expense) expenseFactory.editMonthObject(setExpenseEditorFactoryInputData(expenseIDEdit));
                    session.addRecurExpense(newRecurExpense);
                    ExpenseOD expenseODSuccessEditRecurring = new ExpenseOD("You have updated all changes " +
                            "of this recurring expense!");
                    return expenseOB.success(expenseODSuccessEditRecurring);
                } else {
                    // If this edited expense is no longer recurring, first ExpenseFactory modifies and edits this expense
                    // according to expenseID, then session.deleteRecurExpense() deletes this expense from session.getRecurData().
                    //(Note the targeted expense is now modified to the new name expenseID.getName() and not the old name.)
                    // Consequently, the user shown with a corresponding message.
                    expenseFactory.editMonthObject(setExpenseEditorFactoryInputData(expenseIDEdit));
                    session.deleteRecurExpense(expenseIDEdit.getName());
                    ExpenseOD expenseODSuccessEditRecurring = new ExpenseOD("You have updated all changes " +
                            "of this expense and it is no longer a recurring expense!");
                    return expenseOB.success(expenseODSuccessEditRecurring);
                }
            } else {
                expenseFactory.editMonthObject(setExpenseEditorFactoryInputData(expenseIDEdit));
            }

            //Success edit to expense
            ExpenseOD expenseODSuccessEdit = new ExpenseOD("You have edited an expense!");
            return expenseOB.success(expenseODSuccessEdit);

        } catch (NoSuchElementException e) {
            //NoSuchElementException fail: User tries to edit an expense that does not exist.
            ExpenseOD expenseODFailEdit = new ExpenseOD("There is no such expense in the current month. " +
                    "Please add a new expense or select existing expense!");
            return expenseOB.fail(expenseODFailEdit);
        } catch (NumberFormatException e) {
            // NumberFormatException|NullPointerException fail: User tries to edit Expense value to an invalid number.
            ExpenseOD expenseODFailEdit = new ExpenseOD("Expense value needs to be a number. Please try again!");
            return expenseOB.fail(expenseODFailEdit);
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
    private boolean changeInRecurringInfo(ExpenseID expenseID) {
        boolean isOldExpenseRecurring;
        try {
            findExpense(expenseID.getSession().getRecurData(), expenseID.getOldExpense());
            isOldExpenseRecurring = true;
        } catch (NoSuchElementException e) {
            isOldExpenseRecurring = false;
        }
        return isOldExpenseRecurring != expenseID.getIsRecurringExpense();
    }

    /**
     * Sets the information needed to create an ExpenseCreatorInputData to call createMonthObject method in ExpenseFactory
     *
     * @return ExpenseCreatorInputData MonthObjectFactoryInputData Object specifically used in ExpenseFactory
     * for the createMonthObject method.
     */
    private MonthObjectFactoryInputData setExpenseCreatorFactoryInputData(ExpenseID expenseIDAdd) {
        ExpenseCreatorInputData expenseCreatorInputData = new ExpenseCreatorInputData();
        expenseCreatorInputData.setName(expenseIDAdd.getName());
        expenseCreatorInputData.setValue(toDouble(expenseIDAdd.getValue()));
        expenseCreatorInputData.setCategory(findCategory(month.getCategoryData(), expenseIDAdd.getCategory()));
        return expenseCreatorInputData;
    }

    /**
     * Sets the information needed to edit an ExpenseEditorInputData to call editMonthObject method in ExpenseFactory
     *
     * @return ExpenseEditorInputData MonthObjectFactoryInputData Object specifically used in ExpenseFactory
     * for the editMonthObject method.
     */
    private MonthObjectFactoryInputData setExpenseEditorFactoryInputData(ExpenseID expenseIDEdit) {
        ExpenseEditorInputData expenseEditorInputData = new ExpenseEditorInputData();
        expenseEditorInputData.setName(expenseIDEdit.getName());
        expenseEditorInputData.setExpense(findExpense(month.getExpenseData(), expenseIDEdit.getOldExpense()));
        expenseEditorInputData.setCategory(findCategory(month.getCategoryData(), expenseIDEdit.getCategory()));
        expenseEditorInputData.setValue(toDouble(expenseIDEdit.getValue()));
        return expenseEditorInputData;
    }
}