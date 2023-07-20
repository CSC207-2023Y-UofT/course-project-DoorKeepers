package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Category class
 */
class CategoryTest {

    /**
     * Tests the Category initializer, getName(), and getBudget()
     */
    @Test
    public void CategoryCreateCategory() {
        Category c1 = new Category("Food", 100.00);
        Assertions.assertEquals("Food", c1.getName());
        Assertions.assertEquals(100.00, c1.getBudget());
    }

    /**
     * Tests setName() and getName()
     */
    @Test
    public void CategorySetName() {
        Category c2 = new Category("Food", 100);
        Assertions.assertEquals("Food", c2.getName());
        c2.setName("Groceries");
        Assertions.assertEquals("Groceries", c2.getName());
    }

    /**
     * Tests setBudget() and getBudget()
     */
    @Test
    public void CategorySetBudget() {
        Category c3 = new Category("Food", 100);
        Assertions.assertEquals(100, c3.getBudget());
        c3.setBudget(150);
        Assertions.assertEquals(150, c3.getBudget());
    }

    /**
     * Tests equals() override
     */
    @Test
    public void CategoryCheckEquals() {
        Category c4 = new Category("Food", 100);
        Category c5 = new Category("Food", 50);
        Category c6 = new Category("Clothes", 100);
        Assertions.assertEquals(c4, c5);
        Assertions.assertNotEquals(c4, c6);
    }
}