package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void CategoryCreateCategory() {
        Category c1 = new Category("Food", 100.00);
        Assertions.assertEquals("Food", c1.getName());
        Assertions.assertEquals(100.00, c1.getBudget());
    }
    @Test
    public void CategorySetName() {
        Category c2 = new Category("Food", 100);
        Assertions.assertEquals("Food", c2.getName());
        c2.setName("Groceries");
        Assertions.assertEquals("Groceries", c2.getName());
    }
    @Test
    public void CategorySetBudget() {
        Category c2 = new Category("Food", 100);
        Assertions.assertEquals(100, c2.getBudget());
        c2.setBudget(150);
        Assertions.assertEquals(150, c2.getBudget());
    }
}