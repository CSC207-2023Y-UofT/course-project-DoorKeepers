package use_cases.add_edit_category_use_case;

import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;
import org.junit.jupiter.api.Test;
import views.add_edit_category_views.CategoryP;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryUCITest {
    @Test
    void addCategoryInMonth() throws EntityException {
            /**
             * Tests method by checking if the size of categoryData in MonthlyStorage is correctly updated.
             * Adding two known-to-fail inputs and two successful inputs.
              */
            CategoryP presenter = new CategoryP();
            CategoryIB interactor = new CategoryUCI(presenter);
            SessionStorage sesh = new SessionStorage();
            MonthlyStorage month = new MonthlyStorage(6, 150);
            sesh.addMonth(month);

            CategoryID add_ID_1 = new CategoryID("Salad", 12, 6, sesh, null);
            CategoryID add_ID_same_name = new CategoryID("Salad", 2,6,sesh, null);
            CategoryID add_ID_2 = new CategoryID("Banana",3.9, 6, sesh, null);
            CategoryID add_ID_neg_value = new CategoryID("Sandwich",-3,6, sesh, null);

            interactor.addCategoryInMonth(add_ID_1);
            interactor.addCategoryInMonth(add_ID_2);
            interactor.addCategoryInMonth(add_ID_same_name);
            interactor.addCategoryInMonth(add_ID_neg_value);

            //Expected value is 3 because there is one default Category "Others" upon creation of each MonthlyStorage and two successful entries.
            assertEquals(3, sesh.getMonthlyData(6).getCategoryData().size());

    }
    @Test
    void editCategoryInMonth() throws EntityException {
            /**
             * Tests method by first adding two valid categories three edit attempts: 1 success and 2 fails.
             * Use findCategory() to see if the category_name is successfully edited.
             */
            CategoryP presenter = new CategoryP();
            CategoryIB interactor = new CategoryUCI(presenter);
            SessionStorage sesh = new SessionStorage();
            MonthlyStorage month = new MonthlyStorage(6, 150);
            sesh.addMonth(month);

            CategoryID add_ID_1 = new CategoryID("Salad", 12, 6, sesh, null);
            CategoryID add_ID_2 = new CategoryID("Banana",3.9, 6, sesh, null);
            CategoryID add_ID_3 = new CategoryID("fun",900, 6, sesh, null);
            interactor.addCategoryInMonth(add_ID_1);
            interactor.addCategoryInMonth(add_ID_2);
            interactor.addCategoryInMonth(add_ID_3);


            CategoryID edit_id_1 = new CategoryID("fish n' chips", 17, 6, sesh, "Salad");
            interactor.editCategoryInMonth(edit_id_1);

            CategoryID edit_id_same_name = new CategoryID("fish n' chips", 16, 6, sesh, "Banana");
            interactor.editCategoryInMonth(edit_id_same_name);

            CategoryID edit_id_bad_value = new CategoryID("chips", -5, 6, sesh, "fun");
            interactor.editCategoryInMonth(edit_id_bad_value);

            // Using findCategory() to check if a category with the desired name parameter is in the updated MonthlyStorage Category list.
            assertNotNull(interactor.findCategory(sesh.getMonthlyData(6).getCategoryData(), "fish n' chips")); // success edit
            assertNotNull(interactor.findCategory(sesh.getMonthlyData(6).getCategoryData(), "Banana")); //fail edit category name
            assertNotNull(interactor.findCategory(sesh.getMonthlyData(6).getCategoryData(), "fun")); //fail edit category budget
    }
}