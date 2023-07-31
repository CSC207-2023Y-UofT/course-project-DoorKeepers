package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import views.add_edit_category_views.CategoryP;

import static org.junit.jupiter.api.Assertions.*;

class CategoryUCITest {


    @Test
    void addCategoryInMonth() throws EntityException {
        try {
            CategoryP presenter = new CategoryP();
            CategoryIB interactor = new CategoryUCI(presenter);
            SessionStorage sesh = new SessionStorage();
            MonthlyStorage month = new MonthlyStorage(6, 150);
            sesh.addMonth(month);
            CategoryID add_inputdata = new CategoryID("Salad", 12, 6, sesh);

            interactor.addCategoryInMonth(add_inputdata);
            assertFalse(sesh.getMonthlyData(6).getCategoryData().isEmpty());
            for (Category c : sesh.getMonthlyData(6).getCategoryData()) {
                if (c.getName().equals("Salad")) {
                    assertEquals(c.getBudget(), 12);
                }
            }
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void editCategoryInMonth () throws EntityException {
        try {
            CategoryP presenter = new CategoryP();
            CategoryIB interactor = new CategoryUCI(presenter);
            SessionStorage sesh = new SessionStorage();
            MonthlyStorage month = new MonthlyStorage(6, 150);
            sesh.addMonth(month);

            CategoryID add_inputdata = new CategoryID("Salad", 12, 6, sesh);
            interactor.addCategoryInMonth(add_inputdata);

            CategoryID edit_inputdata = new CategoryID("fish n' chips", 17, 6, sesh, interactor.findCategory(month.getCategoryData(), "Salad"));
            interactor.editCategoryInMonth(edit_inputdata);
            assertNotNull(interactor.findCategory(sesh.getMonthlyData(6).getCategoryData(), "fish n' chips"));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}