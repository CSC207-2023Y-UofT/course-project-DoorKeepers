package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class that tests the CategoryFactory, CategoryCreatorInputData, and CategoryEditorInputData classes.
 * Note: all error handling is done in the CategoryUseCaseInteractor, so all category info in the input data should
 * be valid.
 */
class CategoryFactoryTest {

    /**
     * Tests createMonthObject() with valid category information.
     */
    @Test
    public void CategoryCreate() {
        CategoryCreatorInputData inputData = new CategoryCreatorInputData("Groceries", 100);
        MonthObjectFactory creator = new CategoryFactory();
        MonthObject monthObject = creator.createMonthObject(inputData);

        Category category = (Category) monthObject;
        Assertions.assertEquals(category.getName(), inputData.getName());
        Assertions.assertEquals(category.getBudget(), inputData.getBudget());
    }

    /**
     * Tests editMonthObject() with valid category information and a different name.
     */
    @Test
    public void CategoryEditName() {
        Category c1 = new Category("Dine out", 50);
        CategoryEditorInputData inputData = new CategoryEditorInputData("Eat out", 50, c1);
        MonthObjectFactory editor = new CategoryFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Category category = (Category) monthObject;
        Assertions.assertEquals(category.getName(), inputData.getName());
        Assertions.assertEquals(category.getBudget(), inputData.getBudget());
        Assertions.assertEquals(category, inputData.getCategory());
    }

    /**
     * Tests editMonthObject() with valid category information and a different budget.
     */
    @Test
    public void CategoryEditBudget() {
        Category c1 = new Category("Dine out", 50);
        CategoryEditorInputData inputData = new CategoryEditorInputData("Dine out", 100, c1);
        MonthObjectFactory editor = new CategoryFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Category category = (Category) monthObject;
        Assertions.assertEquals(category.getName(), inputData.getName());
        Assertions.assertEquals(category.getBudget(), inputData.getBudget());
        Assertions.assertEquals(category, inputData.getCategory());
    }

    /**
     * Tests editMonthObject() with valid category information and nothing different.
     * Note: this test is included since The CategoryUseCaseInteractor considers this as a valid option for the user to
     * edit, and sends the request to the CategoryFactory. Thus, it is added to ensure that the CategoryFactory will
     * correctly handle this case.
     */
    @Test
    public void CategoryEditNothing() {
        Category c1 = new Category("Dine out", 50);
        CategoryEditorInputData inputData = new CategoryEditorInputData("Dine out", 50, c1);
        MonthObjectFactory editor = new CategoryFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Category category = (Category) monthObject;
        Assertions.assertEquals(category.getName(), inputData.getName());
        Assertions.assertEquals(category.getBudget(), inputData.getBudget());
        Assertions.assertEquals(category, inputData.getCategory());
    }
}