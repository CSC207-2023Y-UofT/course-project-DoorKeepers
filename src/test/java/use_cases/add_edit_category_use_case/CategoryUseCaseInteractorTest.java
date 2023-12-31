package use_cases.add_edit_category_use_case;

import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.add_edit_category_views.CategoryPresenter;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * CategoryUseCaseInteractorTest contains tests for the Category use cases (add and edit category).
 */
class CategoryUseCaseInteractorTest {

    private static SessionStorage session;

    /**
     * Creates a SessionStorage for the following test cases.
     */
    @BeforeAll
    public static void categorySetUp() {
        session = new SessionStorage();
    }

    /**
     * Tests success add case by checking if the size of categoryData in MonthlyStorage is correctly updated.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void addCategoryInMonthSuccess() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(6, 150);
        session.addMonth(monthAdd);

        CategoryInputData addID1 = new CategoryInputData("Salad", 12, 6, session, null);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("You have added a new category!", interactor.addCategoryInMonth(addID1).getMessage());
        //Expected value is 2 because there is one default Category "Others" upon creation of each MonthlyStorage and one successful entry.
        assertEquals(2, session.getMonthlyData(6).getCategoryData().size());
        assertEquals(addID1.getName(), session.getMonthlyData(6).getCategoryData().get(1).getName());
    }

    /**
     * Tests fail add case when user tries to add a new Category name that exists in the MonthlyStorage.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void addCategoryInMonthSameNameFail() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(5, 150);
        session.addMonth(monthAdd);

        CategoryInputData addIDSameName = new CategoryInputData("Salad", 2, 5, session, null);
        CategoryInputData addID1 = new CategoryInputData("Salad", 12, 5, session, null);

        interactor.addCategoryInMonth(addID1);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is already a category with this new name in this month.", interactor.addCategoryInMonth(addIDSameName).getMessage());
        //Expected value is 2 because there is one default Category "Others" upon creation of each MonthlyStorage one successful, and one failed entry.
        assertEquals(2, session.getMonthlyData(5).getCategoryData().size());
    }

    /**
     * Tests fail add case when user tries to add a new Category budget that is a negative number.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void addCategoryInMonthNegValueFail() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(4, 150);
        session.addMonth(monthAdd);

        CategoryInputData addIDNegValue = new CategoryInputData("Sandwich", -3, 4, session, null);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Category budget can't be less than $0. Please try again!", interactor.addCategoryInMonth(addIDNegValue).getMessage());
        //Expected value is 1 because there is one default Category "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(1, session.getMonthlyData(4).getCategoryData().size());
    }

    /**
     * Tests fail add case when user tries to add a new Category budget that is an invalid double.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void addCategoryInMonthInvalidDoubleFail() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthAdd = new MonthlyStorage(3, 150);
        session.addMonth(monthAdd);
        CategoryInputData addIDInvalidDouble = new CategoryInputData("Salad", "a", 3, session, null);

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Category budget needs to be a number. Please try again!", interactor.addCategoryInMonth(addIDInvalidDouble).getMessage());
        //Expected value is 1 because there is one default Category "Others" upon creation of each MonthlyStorage and one failed entry.
        assertEquals(1, session.getMonthlyData(3).getCategoryData().size());
    }

    /**
     * Tests success edit use case by adding a valid category and then a successful edit.
     * Use findCategory() to see if the category name is successfully edited.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void editCategoryInMonthSuccess() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(7, 150);
        session.addMonth(monthEdit);
        CategoryInputData addID = new CategoryInputData("Salad", 12, 7, session, null);
        interactor.addCategoryInMonth(addID);

        CategoryInputData editId1 = new CategoryInputData("fish n' chips", 12, 7, session, "Salad");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("You have edited a category!", interactor.editCategoryInMonth(editId1).getMessage());
        // Using findCategory() to check if a category with the desired name parameter is in the updated MonthlyStorage Category list.
        interactor.findCategory(session.getMonthlyData(7).getCategoryData(), "fish n' chips");
    }

    /**
     * Tests fail edit case when user tries to edit the Category name to another name that exists in MonthlyStorage.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void editCategoryInMonthSameNameFail() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(8, 150);
        session.addMonth(monthEdit);

        CategoryInputData addID = new CategoryInputData("Salad", 12, 8, session, null);
        CategoryInputData addID2 = new CategoryInputData("Banana", 3.9, 8, session, null);
        CategoryInputData addID3 = new CategoryInputData("fun", 900, 8, session, null);
        interactor.addCategoryInMonth(addID);
        interactor.addCategoryInMonth(addID2);
        interactor.addCategoryInMonth(addID3);

        CategoryInputData editIdSameName = new CategoryInputData("Banana", 12, 8, session, "Salad");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("There is already a category with this new name in this month.", interactor.editCategoryInMonth(editIdSameName).getMessage());
        //Fail to edit Category budget when tries to also edit Category name, but it is an existing Category name.
        assertNotEquals(interactor.findCategory(session.getMonthlyData(8).getCategoryData(), "Banana").getBudget(), 12, 0.0);
    }

    /**
     * Tests fail edit case when user tries to edit the Category budget into a negative number.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void editCategoryInMonthNegValueFail() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthEdit = new MonthlyStorage(9, 150);
        session.addMonth(monthEdit);

        CategoryInputData addID = new CategoryInputData("Salad", 12, 9, session, null);
        interactor.addCategoryInMonth(addID);

        CategoryInputData editIdNegValue = new CategoryInputData("Banana", -12, 9, session, "Salad");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Category budget can't be less than $0. Please try again!", interactor.editCategoryInMonth(editIdNegValue).getMessage());
        //Fail to edit Category name when tries to also edit Category budget, but it is a new budget that is a negative number.
        Assertions.assertThrows(NoSuchElementException.class, () -> interactor.findCategory(session.getMonthlyData(9).getCategoryData(), "Banana"));
    }

    /**
     * Tests fail edit case when user tries to edit the Category budget into an invalid double.
     *
     * @throws EntityException if a valid method call throws, so that should fail the test
     */
    @Test
    void editCategoryInMonthInvalidDoubleFail() throws EntityException {
        CategoryPresenter presenter = new CategoryPresenter();
        CategoryUseCaseInteractor interactor = new CategoryUseCaseInteractor(presenter);
        MonthlyStorage monthlyStorage = new MonthlyStorage(11, 150);
        session.addMonth(monthlyStorage);

        CategoryInputData addID = new CategoryInputData("Salad", 12, 11, session, null);
        interactor.addCategoryInMonth(addID);

        CategoryInputData editIDInvalidDouble = new CategoryInputData("Salad", "a", 11, session, "Salad");

        // Check if the correct message is returned corresponding to the situation.
        assertEquals("Category budget needs to be a number. Please try again!", interactor.editCategoryInMonth(editIDInvalidDouble).getMessage());
        //Fail to edit Category budget, so Category budget should stay the same.
        assertEquals(interactor.findCategory(session.getMonthlyData(11).getCategoryData(), "Salad").getBudget(), 12, 0.0);
    }
}