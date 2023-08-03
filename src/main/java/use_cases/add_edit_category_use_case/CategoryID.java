package use_cases.add_edit_category_use_case;

import entities.SessionStorage;

/**
 * CategoryID contains all user inputs.
 * Created in CategoryC and used in CategoryUCI to perform addCategoryInMonth() and editCategoryInMonth().
 */

public class CategoryID {
    private final String name;
    private Object value;
    private final int monthID;

    private final SessionStorage session;
    private final String oldCategory;

    /**
     * Constructs CategoryID for adding/editing an existing Category Object.
     * @param name Category name
     * @param value Category budget
     * @param monthID An int representing the month which the Category Object belongs to.
     * @param session The current session which the MonthlyStorage Object belongs to.
     * @param oldCategory An existing Category in the MonthlyStorage Object the user wish to edit.
     */
    public CategoryID(String name, Object value, int monthID, SessionStorage session, String oldCategory) {
        this.name = name;
        this.value = value;
        this.monthID = monthID;
        this.session = session;
        this.oldCategory = oldCategory;
    }
    public String getName(){return name;}
    public Object getValue(){return value;}
    public void setValue(double value){this.value = value;}
    public int getMonthID(){return monthID;}
    public SessionStorage getSession(){return session;}
    public String getOldCategory(){return oldCategory;}
}
