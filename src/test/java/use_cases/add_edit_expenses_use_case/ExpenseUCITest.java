package use_cases.add_edit_expenses_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.add_edit_epense_views.ExpenseP;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseUCITest {
    //private  SessionStorage session;
    private static Category food;

    /**
     Creates a SessionStorage for the following test cases.
     */
    @BeforeAll
    public static void expenseSetUp() {
        food = new Category("Food", 30);
    }

    /**
     Tests success add case by checking if the size of expenseData in MonthlyStorage is correctly updated.
     */
    @Test
    void addExpenseInMonthSuccess() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(1, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,1, session, null);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("You have added a new expense!", interactor.addExpenseInMonth(addID).getMessage());
        assertEquals(1, session.getMonthlyData(1).getExpenseData().size());
    }

    /**
     * Tests fail add case when user tries to add a new Expense name that exists in the MonthlyStorage.
     */
    @Test
    void addExpenseInMonthSameNameFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(2, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addIDSameName = new ExpenseID("Sandwich", 3, "Food", false ,2, session, null);
        ExpenseID addID1 = new ExpenseID("Sandwich", 5, "Food", false,2, session, null);
        interactor.addExpenseInMonth(addID1);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is already a expense with this new name in this month.", interactor.addExpenseInMonth(addIDSameName).getMessage());
        //Expected value is 2 because there is one default Expense "Others" upon creation of each MonthlyStorage one successful, and one failed entry.
        assertEquals(1, session.getMonthlyData(2).getExpenseData().size());
    }

    /**
     * Tests fail add case when user tries to add a new Expense value that is a negative number.
     */
    @Test
    void addExpenseInMonthNegValueFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(3, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addIDNegValue = new ExpenseID("Sandwich", -3, "Food", true,3, session, null);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Expense value can't be less than $0. Please try again!", interactor.addExpenseInMonth(addIDNegValue).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(0, session.getMonthlyData(3).getExpenseData().size());
    }

     /**
      * Tests success add case by checking if the size of expenseData in recurringData is correctly updated.
     */
    @Test
    void addExpenseInMonthInvalidDoubleFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(4, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addIDInvalidDouble = new ExpenseID("Sandwich", "a", "Food", true,4, session, null);
        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Expense value is needs to be a number. Please try again!", interactor.addExpenseInMonth(addIDInvalidDouble).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(0, session.getMonthlyData(4).getExpenseData().size());
    }

    /**
     * Tests fail add case when user tries to add a new Expense name that exists in the recurringData.
     */
    @Test
    void addExpenseInRecurringSuccess() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(5, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Other", true,5, session, null);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("You have created a new recurring expense!", interactor.addExpenseInMonth(addID).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(1, session.getRecurData().size());    }
    @Test
    void addExpenseInRecurringSameNameFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(6, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Other", true,6, session, null);
        interactor.addExpenseInMonth(addID);
        ExpenseID addIDSameName = new ExpenseID("Sandwich", 5, "Other", true ,6, session, null);

        interactor.addExpenseInMonth(addIDSameName);
        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is a recurring expense with this name, you don't need to add expense in month. " +
                "(If this is not the same expense, please use another name!)", interactor.addExpenseInMonth(addIDSameName).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(1, session.getRecurData().size());    }

    /**
     * Tests success edit use case by adding one valid expense and then a successful edit expense name.
     * Use findExpense() to see if the expense_name is successfully edited.
     */
    @Test
    void editExpenseInMonthSuccessName() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(7, 150);
        session.addMonth(monthEdit);
        monthEdit.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,7, session, null);
        interactor.addExpenseInMonth(addID);

        ExpenseID editID = new ExpenseID("Salad", 3, "Food", false,7, session, "Sandwich");

        // Check if the correct message is returned corresponding to the situation.

        assertEquals("You have edited an expense!", interactor.editExpenseInMonth(editID).getMessage());
        // Using findExpense() to check if an expense with the desired name parameter is in the updated MonthlyStorage Expense list.
        Assertions.assertDoesNotThrow(() -> interactor.findExpense(session.getMonthlyData(7).getExpenseData(), "Salad"));}
    /**
     * Tests success edit use case by adding one valid expense and then a successful edit expense value.
     * Use findExpense() to see if the expense_name is successfully edited.
     */
    @Test
    void editExpenseInMonthSuccessValue() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(7, 150);
        session.addMonth(monthEdit);
        monthEdit.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,7, session, null);
        interactor.addExpenseInMonth(addID);

        ExpenseID editID = new ExpenseID("Sandwich", 5, "Food", false,7, session, "Sandwich");

        // Check if the correct message is returned corresponding to the situation.

        assertEquals("You have edited an expense!", interactor.editExpenseInMonth(editID).getMessage());
        // Using findExpense() to check if an expense with the desired name parameter is in the updated MonthlyStorage Expense list.
        assertEquals(5, interactor.findExpense(monthEdit.getExpenseData(), "Sandwich").getValue());}

    /**
     * Tests fail edit case when user tries to edit the Expense name to another name that exists in MonthlyStorage.
     */
    @Test
    void editExpenseInMonthSameNameFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(8, 150);
        session.addMonth(monthEdit);
        monthEdit.addCategory(food);


        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,8, session, null);
        ExpenseID addID2 = new ExpenseID("Banana", 3, "Food", false,8, session, null);
        ExpenseID addID3 = new ExpenseID("Coffee", 3, "Food", false,8, session, null);
        interactor.addExpenseInMonth(addID);
        interactor.addExpenseInMonth(addID2);
        interactor.addExpenseInMonth(addID3);

        ExpenseID editIdSameName = new ExpenseID("Sandwich", 12, "Food", false,8, session, "Banana");
        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is already a expense with this new name in this month.", interactor.editExpenseInMonth(editIdSameName).getMessage());
        //Fail to edit Expense value when tries to also edit Expense name, but it is an existing Expense name.
        assertNotEquals(interactor.findExpense(session.getMonthlyData(8).getExpenseData(), "Banana").getValue(), 12, 0.0);
    }

    /**
     * Tests fail edit case when user tries to edit the Expense value into a negative number.
     */
    @Test
    void editExpenseInMonthNegValueFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(9, 150);
        session.addMonth(monthEdit);
        monthEdit.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,9, session, null);
        interactor.addExpenseInMonth(addID);

        ExpenseID editIdNegValue = new ExpenseID("Banana", -3, "Food", false,9, session, "Sandwich");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Expense value can't be less than $0. Please try again!", interactor.editExpenseInMonth(editIdNegValue).getMessage());
        //Fail to edit Expense name when tries to also edit Expense value, but it is a new value that is a negative number.
        Assertions.assertThrows(NoSuchElementException.class, () -> interactor.findExpense(session.getMonthlyData(9).getExpenseData(), "Banana"));
    }

    /**
     * Tests fail edit case when user tries to edit an Expense that does not exist in MonthlyStorage.
     */
    @Test
    void editExpenseInMonthNoExpenseFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(10, 150);
        session.addMonth(monthEdit);
        monthEdit.addCategory(food);

        ExpenseID editIdNegValue = new ExpenseID("Banana", -3, "Food", false,10, session, "Sandwich");
        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is no such expense in the current month. Please add a new expense or select existing expense!", interactor.editExpenseInMonth(editIdNegValue).getMessage());
        //Fail to edit Expense name, but the old_expense is not found in MonthlyStorage.
        Assertions.assertThrows(NoSuchElementException.class, () -> interactor.findExpense(session.getMonthlyData(10).getExpenseData(), "Banana"));
    }

    /**
     * Tests fail edit case when user tries to edit the Expense value into an invalid double.
     */
    @Test
    void editExpenseInMonthInvalidDoubleFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthlyStorage = new MonthlyStorage(11, 150);
        session.addMonth(monthlyStorage);
        monthlyStorage.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,11, session, null);
        interactor.addExpenseInMonth(addID);

        ExpenseID editIDInvalidDouble = new ExpenseID("Sandwich", "a", "Food", false,11, session, "Sandwich");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Expense value needs to be a number. Please try again!", interactor.editExpenseInMonth(editIDInvalidDouble).getMessage());
        //Fail to edit Expense value, so Expense value should stay the same.
        assertEquals(interactor.findExpense(session.getMonthlyData(11).getExpenseData(), "Sandwich").getValue(), 3, 0.0);
    }

    @Test
    void editExpenseInRecurringSuccessMovetoSession() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(12, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Food", false,12, session, null);
        interactor.addExpenseInMonth(addID);
        String oldExpense = interactor.findExpense(monthAdd.getExpenseData(),"Sandwich").getName();

        ExpenseID editID = new ExpenseID("Sandwich", 3, "Other", true,12, session, oldExpense);
        // Check if the correct message is returned corresponding to the situation.
        assertEquals("You have updated all changes of this new recurring expense!", interactor.editExpenseInMonth(editID).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(1, session.getRecurData().size());    }

    @Test
    void editExpenseInRecurringSuccessMovetoMonth() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(13, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Other", true,13, session, null);
        interactor.addExpenseInMonth(addID);
        //String oldExpense = interactor.findExpense(session.getRecurData(),"Sandwich").getName();
        System.out.println(monthAdd.getExpenseData());

        ExpenseID editID = new ExpenseID("Sandwich", 3, "Food", false,13, session, "Sandwich");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("You have updated all changes of this expense and it is no longer a recurring expense!", interactor.editExpenseInMonth(editID).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(0, session.getRecurData().size());    }
    @Test
    void editExpenseInRecurringSameNameFail() throws EntityException {
        SessionStorage session = new SessionStorage();
        ExpenseP presenter = new ExpenseP();
        ExpenseUCI interactor = new ExpenseUCI(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(14, 150);
        session.addMonth(monthAdd);
        monthAdd.addCategory(food);

        ExpenseID addID = new ExpenseID("Sandwich", 3, "Other", true, 14, session, null);
        interactor.addExpenseInMonth(addID);
        ExpenseID editIDSameName = new ExpenseID("Sandwich", 5, "Food", true, 14, session, "Sandwich");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is a recurring expense with this name, you don't need to add recurring expense in month! " +
                "(If this is not the same expense, please use another name!)", interactor.editExpenseInMonth(editIDSameName).getMessage());
        //Expected value is 1 because there is one default Expense "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(1, session.getRecurData().size());
    }}