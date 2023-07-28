package use_cases.monthly_menu;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UpdateViewUCITest {
    static Category category1;
    static Category category2;
    static Category other;
    static Expense expense1;
    static Expense expense2;
    static int monthID;
    static MonthlyStorage monthlyStorage;
    static SessionStorage sessionStorage;

    @BeforeEach
    void setUp() {
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        expense1 = new Expense("Loblaws", category1, 50.00 );
        expense2 = new Expense("Indigo", category2, 30.00 );
        other = new Category("Other", 0);
        monthID = 202307;
        monthlyStorage = new MonthlyStorage(202307,100);
        sessionStorage = new SessionStorage();
        sessionStorage.addMonth(monthlyStorage);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOutputNewMonthSuccess() {
        UpdateViewIB interactor = new UpdateViewUCI();
        UpdateViewID inputData = new UpdateViewID(sessionStorage, monthID);

        ArrayList<Expense> expenses = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(other);
        MonthMenuOD actualOutput = interactor.createOutput(inputData);
        Assertions.assertEquals(expenses,actualOutput.getExpenseData());
        Assertions.assertEquals(categories,actualOutput.getCategoryData());
    }

    @Test
    void createOutputNormalMonthSuccess() throws EntityException {
            UpdateViewIB interactor = new UpdateViewUCI();
            monthlyStorage.addCategory(category1);
            monthlyStorage.addCategory(category2);
            monthlyStorage.addExpense(expense1);
            monthlyStorage.addExpense(expense2);
            UpdateViewID inputData = new UpdateViewID(sessionStorage, monthID);

            ArrayList<Expense> expenses = new ArrayList<>();
            expenses.add(expense1);
            expenses.add(expense2);
            ArrayList<Category> categories = new ArrayList<>();
            categories.add(other);
            categories.add(category1);
            categories.add(category2);
            MonthMenuOD actualOutput = interactor.createOutput(inputData);
            Assertions.assertEquals(expenses,actualOutput.getExpenseData());
            Assertions.assertEquals(categories,actualOutput.getCategoryData());
    }

    @Test
    void createOutputFail(){
            UpdateViewIB interactor = new UpdateViewUCI();
            UpdateViewID inputData = new UpdateViewID(sessionStorage, 202207);

            MonthMenuOD actualOutput = interactor.createOutput(inputData);
            Assertions.assertEquals("Something went wrong, please try again.",actualOutput.getWarning());
    }
}