package use_cases.add_edit_category_use_case;

import entities.SessionStorage;

/**
 * CategoryID contains all user inputs in terms of business logic items used in the programme.
 */

public class CategoryID {
    private final String name;
    private Object value;
    private final int monthID;

    private final SessionStorage session;
    private final String old_category;

    /**
     * Constructs Category_Inout_Data for adding/editing an existing Category Object.
     * @param name Category name
     * @param value Category budget
     * @param monthID An int representing the month which the Category Object belongs to.
     * @param session The current session which the MonthlyStorage Object belongs to.
     * @param old_category An existing Category in the MonthlyStorage Object the user wish to edit.
     */
    public CategoryID(String name, Object value, int monthID, SessionStorage session, String old_category) {
        this.name = name;
        this.value = value;
        this.monthID = monthID;
        this.session = session;
        this.old_category = old_category;
    }
    public String getName(){return name;}
    public Object getValue(){return value;}
    public void setValue(double value){this.value = value;}
    public int getMonthID(){return monthID;}
    public SessionStorage getSession(){return session;}
    public String getOld_category(){return old_category;}

}
