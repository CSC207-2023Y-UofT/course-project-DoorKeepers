package use_cases.add_edit_category_use_case;

import entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import views.add_edit_category_views.CategoryP;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPTest {

    @Test
    void success_add() {
        try {
            //CategoryOD categoryOD_add = new CategoryOD(new Category("Concert", 265));
            CategoryP presenter = new CategoryP();
        } catch (Exception e) {
            Assertions.fail();
        }
    }
    @Test
    void fail() {
        CategoryP presenter = new CategoryP();
        assertEquals(presenter.fail("test_fail_method").getMessage(), "test_fail_method");
    }
    @Test
    void success_edit() {
        try {
            //CategoryOD category_OD_edit = new CategoryOD((new Category("coffee", 5.5)));
            CategoryP presenter = new CategoryP();
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}