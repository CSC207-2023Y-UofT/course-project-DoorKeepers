package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class that tests the Category class.
 */
class CategoryTest {

    /**
     * Tests the Category initializer, getName(), and getBudget() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void CategoryCreateCategory() {
        Category c1 = new Category("Food", 100.00);
        Assertions.assertEquals("Food", c1.getName());
        Assertions.assertEquals(100.00, c1.getBudget());
    }

    /**
     * Tests setName() and getName() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void CategorySetName() {
        Category c2 = new Category("Food", 100);
        Assertions.assertEquals("Food", c2.getName());
        c2.setName("Groceries");
        Assertions.assertEquals("Groceries", c2.getName());
    }

    /**
     * Tests setBudget() and getBudget() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void CategorySetBudget() {
        Category c3 = new Category("Food", 100);
        Assertions.assertEquals(100, c3.getBudget());
        c3.setBudget(150);
        Assertions.assertEquals(150, c3.getBudget());
    }

    /**
     * Tests equals() override on two Category objects with the same name.
     */
    @Test
    public void CategoryCheckEqualsSameName() {
        Category c4 = new Category("Food", 100);
        Category c5 = new Category("Food", 50);
        Assertions.assertEquals(c4, c5);
    }

    /**
     * Tests equals() override on two Category objects with the same budget.
     */
    @Test
    public void CategoryCheckEqualsSameBudget() {
        Category c4 = new Category("Food", 100);
        Category c6 = new Category("Clothes", 100);
        Assertions.assertNotEquals(c4, c6);
    }

    /**
     * Tests equals() override on two Category objects initialized with the same name, but one is changed.
     * Note: This is important because when we edit a Category, we need to check if this new Category is equal to
     * another Category in the month.
     */
    @Test
    public void CategoryCheckEqualsChangeName() {
        Category c4 = new Category("Food", 100);
        Category c5 = new Category("Groceries", 50);
        Category c6 = new Category("Food", 100);
        Assertions.assertEquals(c4, c6);
        Assertions.assertNotEquals(c5, c6);
        c6.setName("Groceries");
        Assertions.assertNotEquals(c4, c6);
        Assertions.assertEquals(c5, c6);
    }
}