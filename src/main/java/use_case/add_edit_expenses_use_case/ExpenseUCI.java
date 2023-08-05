package use_case.add_edit_expenses_use_case;

import entities.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ExpenseUCI implements ExpenseIB {
    final ExpenseOB expenseOB;
    private ExpenseID expenseID;
    private ArrayList<Expense> recurringExpenseList;


    /**
     * Constructs ExpenseUCI.
     *
     * @param expenseP presenter that is related to the use case.
     */
    public ExpenseUCI(ExpenseOB expenseP) {
        this.expenseOB = expenseP;
    }

    /**
     * Helper method returns a double to check if the input Object is a valid double.
     *
     * @param value a user input
     * @return double converted from value
     */
    public static double toDouble(Object value) throws NumberFormatException, NullPointerException {
        return Double.parseDouble(String.valueOf(value));
    }

    /**
     * Overrides method in ExpenseIB.
     * Attempts to add an expense with information from ExpenseID and returns a ExpenseOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below:
     * 1. NumberFormatException: User tries to add a new budget value that can not be converted to a double.
     * 2. Expense budget less than 0: User tries to add a new budget value that is a negative number.
     * 3. EntityException: User tries to add an invalid Expense name but failed. (See entities/EntityException.java)
     * 4. Same recurring expense name: User tries to add an expense name that is a saved recurring expense in the current SessionStorage.
     *
     * @param expenseIDAdd ExpenseID required for adding a new expense to the designated monthID MonthlyStorage Object.
     * @return ExpenseOD String message indicating success/fail add attempt.
     * @throws EntityException thrown when expenseIDAdd has invalid expense information.
     */
    @Override
    public ExpenseOD addExpenseInMonth(ExpenseID expenseIDAdd) throws EntityException {
        this.expenseID = expenseIDAdd;
        SessionStorage session = expenseIDAdd.getSession();
        MonthlyStorage month = expenseIDAdd.getSession().getMonthlyData(expenseIDAdd.getMonthID());
        ArrayList<Expense> monthExpenseList = month.getExpenseData();
        ArrayList<Category> categoryList = month.getCategoryData();
        this.recurringExpenseList = session.getRecurData();

        try {
            double valueDouble = toDouble(expenseIDAdd.getValue());

            Category selectedCategory = findCategory(categoryList, expenseID.getOldCategory());
            Category other = findCategory(categoryList, "Other");

            if (valueDouble < 0) {
                // 2. Expense budget less than 0 fail
                ExpenseOD expenseODFailAdd = new ExpenseOD("Expense value can't be less than $0. Please try again!");
                return expenseOB.fail(expenseODFailAdd);
            }
            for (Expense expense1 : monthExpenseList) {
                if (expense1.getName().equals(expenseID.getName())) {
                    //Repeated name in month
                    ExpenseOD expenseODFailEdit = new ExpenseOD("There is already a expense with this new name in this month.");
                    return expenseOB.fail(expenseODFailEdit);
                }
            }

            if (expenseID.getIsRecurringExpense()) {
                if(recurringExpenseList.size() > 0) {
                    for (Expense expense1 : recurringExpenseList) {
                        if (expense1.getName().equals(expenseID.getName())) {
                            //Repeated name in recurringData
                            ExpenseOD expenseODFailAdd = new ExpenseOD("There is a recurring expense with this name, you don't need to add expense in month. " +
                                    "(If this is not the same expense, please use another name!)");
                            return expenseOB.fail(expenseODFailAdd);
                        }
                    }
                }else{
                    Expense newrecurExpense = new Expense(expenseID.getName(), other, valueDouble);
                    session.addRecurExpense(newrecurExpense);
                    ExpenseOD expenseODSuccessAdd = new ExpenseOD("You have created a new recurring expense!");
                    return expenseOB.success(expenseODSuccessAdd);}
                    }

            Expense newExpense = new Expense(expenseID.getName(), selectedCategory,valueDouble);

            month.addExpense(newExpense);
            ExpenseOD expenseODSuccessAdd = new ExpenseOD("You have added a new expense!");
            return expenseOB.success(expenseODSuccessAdd);

        } catch (NumberFormatException | NullPointerException e) {
            //1. NumberFormatException/NullPointerException fail
            ExpenseOD expenseODFailAdd = new ExpenseOD("Expense value is needs to be a number. Please try again!");
            return expenseOB.fail(expenseODFailAdd);

        } catch (EntityException e) {
            //3. EntityException fail
            ExpenseOD expenseODFailAdd = new ExpenseOD("There is already a expense with this new name in this month.");
            return expenseOB.fail(expenseODFailAdd);
        } catch(NoSuchElementException e){
        //2. NoSuchElementException fail
        ExpenseOD expenseODFailEdit = new ExpenseOD("There is no such expense in the current month. Please add a new expense or select existing expense!");
        return expenseOB.fail(expenseODFailEdit);
    }}

    /**
     * Attempts to edit an expense with information from ExpenseID and returns a ExpenseOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below:
     * 1. Repeated Name: User tries to edit expense name to another name that exists in the month.
     * 2. NoSuchElementException: User tries to edit an expense that does not exist.
     * 3. NumberFormatException|NullPointerException: User tries to edit a budget value with input that can not be converted to a double.
     * 4. Expense budget less than 0: User tries to edit a budget value with input that is a negative number.
     *
     * @param expenseIDEdit ExpenseID required for editing a new expense to the designated monthID MonthlyStorage Object.
     * @return ExpenseOD String message indicating success/fail add attempt.
     */
    @Override
    public ExpenseOD editExpenseInMonth(ExpenseID expenseIDEdit) throws EntityException {
        this.expenseID = expenseIDEdit;
        SessionStorage session = expenseID.getSession();
        MonthlyStorage month = session.getMonthlyData(expenseID.getMonthID());
        ArrayList<Expense> monthExpenseList = month.getExpenseData();
        ArrayList<Category> categoryList = month.getCategoryData();
        Category selectedCategory = findCategory(categoryList, expenseID.getOldCategory());

        try {
            Expense selectedExpense = findExpense(monthExpenseList, expenseID.getOldExpense());
            double valueDouble = toDouble(expenseIDEdit.getValue());
            if (valueDouble < 0) {
                //4. Expense budget less than 0 fail
                ExpenseOD expenseODFailEdit = new ExpenseOD("Expense budget can't be less than $0. Please try again!");
                return expenseOB.fail(expenseODFailEdit);
            }
            for (Expense expense1 : monthExpenseList) {
                if (expense1.getName().equals(expenseID.getName())) {
                    //1. Repeated name fail
                    ExpenseOD expenseODFailEdit = new ExpenseOD("There is already a expense with this new name in this month.");
                    return expenseOB.fail(expenseODFailEdit);
                }
            }

            if (changeInRecurringInfo()) {// 4. Same recurring expense name fail
                if (!expenseID.getIsRecurringExpense()) {
                    for (Expense expense2 : monthExpenseList) {
                        if (expense2.getName().equals(expenseID.getName())) {
                            ExpenseOD expenseODFailEdit = new ExpenseOD("There is a recurring expense with this name, you don't need to add recurring expense in month! " +
                                    "(If this is not the same expense, please use another name!)");
                            return expenseOB.fail(expenseODFailEdit);
                        } else {
                            session.deleteRecurExpense(expenseID.getName());
                            selectedExpense.setCategory(selectedCategory);}
                    }
                } else {
                    session.addRecurExpense(selectedExpense);
                    selectedExpense.setCategory(selectedCategory);}
            }
            selectedExpense.setName(expenseIDEdit.getName());
            expenseIDEdit.setValue(valueDouble);
            selectedExpense.setValue(valueDouble);

            ExpenseOD expenseODSuccessEdit = new ExpenseOD("You have edited a expense!");
            return expenseOB.success(expenseODSuccessEdit);

            } catch(NoSuchElementException e){
                //2. NoSuchElementException fail
                ExpenseOD expenseODFailEdit = new ExpenseOD("There is no such expense in the current month. Please add a new expense or select existing expense!");
                return expenseOB.fail(expenseODFailEdit);
            } catch(NumberFormatException|NullPointerException e){
                //3. NumberFormatException|NullPointerException fail
                ExpenseOD expenseODFailEdit = new ExpenseOD("Expense budget needs to be a number. Please try again!");
                return expenseOB.fail(expenseODFailEdit);
            }
    }

    /**
     * @param ExpenseData An ArrayList of expenses.
     * @param name Expense name.
     * //@return
     * //@throws NoSuchElementException
     */

        public Expense findExpense(ArrayList<Expense> ExpenseData, String name)throws NoSuchElementException{
            for (Expense expense : ExpenseData) {
                if (Objects.equals(expense.getName(), name)) {
                    return expense;
                }
            }
            throw new NoSuchElementException();
        }

    /**
     * @param monthCategoryData An ArrayList of categories.
     * @param name Category name.
     * @return
     * @throws NoSuchElementException
     */
    public Category findCategory (ArrayList < Category > monthCategoryData, String name) throws NoSuchElementException {
            for (Category category : monthCategoryData) {
                if (Objects.equals(category.getName(), name)) {
                    return category;
                }
            }
            throw new NoSuchElementException();
        }


    /**
     * Identifying the only two cases when Session.recurringExpenseData needs to updated when attempting to edit Expense.
     * (The user changes their response for the isRecurringExpense checkbox)
     * @return boolean suggesting there is a need to update Session.recurringExpenseData
     */
    private boolean changeInRecurringInfo() {
                // check if new expense name exists as a recurring expense
        if(expenseID.getIsRecurringExpense()) {
            try {findExpense(recurringExpenseList, expenseID.getName());
                return false;
            }catch (NoSuchElementException e){
                return true;}
        }else{
            try{findExpense(recurringExpenseList, expenseID.getName());
            return true;
            }catch(NoSuchElementException e) {
                return false;}
        }
    }
}


