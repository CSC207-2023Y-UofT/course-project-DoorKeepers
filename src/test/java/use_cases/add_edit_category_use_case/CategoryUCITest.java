package use_cases.add_edit_category_use_case;

import entities.EntityException;
import entities.SessionStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryUCITest {

    @Test
    void addCategory() throws EntityException {
        CategoryP presenter = new CategoryP();
        CategoryIB interactor = new CategoryUCI(presenter);
        SessionStorage sesh = new SessionStorage();
        CategoryID add_inputdata = new CategoryID("Salad", 12, 6, sesh);
        CategoryID edit_inputdata = new CategoryID("fish n' chips", 17, 6, sesh);

        interactor.addCategoryInMonth(add_inputdata);
        interactor.editCategoryInMonth(edit_inputdata);

        assertEquals()

    }

    @Test
    void editCategory() {
    }
}