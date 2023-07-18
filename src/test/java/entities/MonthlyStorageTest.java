package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class MonthlyStorageTest {

    static Category category1;
    static Category category2;
    static Expense expense1;
    static Expense expense2;

    @BeforeAll
    public static void MonthlyStorageCreateBaseEntities(){
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        expense1 = new Expense("Loblaws", category1, 50.00 );
        expense2 = new Expense("Indigo", category2, 30.00 );
    }

    @Test
    public void MonthlyStorageCreateMonth() {
        MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
        Assertions.assertEquals(1, month1.getCurrentMonth());
        Assertions.assertEquals(1000, month1.getMonthlyBudget());
    }

    @Test
    public void MonthlyStorageSetExpenseData() {
        MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
        month1.setExpenseData(expense1);
        month1.setExpenseData(expense2);
        ArrayList<Expense> expected1 = new ArrayList<>();
        expected1.add(expense1);
        expected1.add(expense2);
        Assertions.assertEquals(expected1, month1.getExpenseData());
    }

    @Test
    public void MonthlyStorageSetCategoryData() {
        MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
        month1.setCategoryData(category1);
        month1.setCategoryData(category2);
        ArrayList<Category> expected2 = new ArrayList<>();
        Category other = new Category("Other", 0);
        expected2.add(other);
        expected2.add(category1);
        expected2.add(category2);
        ArrayList<Category> actual = month1.getCategoryData();
        Assertions.assertEquals(expected2.get(0).getName(), actual.get(0).getName());
        Assertions.assertEquals(expected2.get(1), actual.get(1));
        Assertions.assertEquals(expected2.get(2), actual.get(2));
    }

}