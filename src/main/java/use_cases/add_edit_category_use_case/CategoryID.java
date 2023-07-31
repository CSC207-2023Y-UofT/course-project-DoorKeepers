package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.SessionStorage;

public class CategoryID {
    private String name;
    private Object value;
    private final int monthID;

    private final SessionStorage session;
    private Category old_category;

    public CategoryID(String name, Object value, int monthID, SessionStorage session){
        this.name = name;
        this.value = value;
        this.monthID = monthID;
        this.session = session;
    }
    public CategoryID(String name, Object value, int monthID, SessionStorage session, Category old_category) {
        this.name = name;
        this.value = value;
        this.monthID = monthID;
        this.session = session;
        this.old_category = old_category;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public double getValue(){return (double) value;}
    public void setValue(double value){this.value = value;}

    public int getMonthID(){return monthID;}
    public SessionStorage getSession(){return session;}
    public Category getOld_category(){return old_category;}
    public void setOld_category(Category old_category){this.old_category = old_category;}

}
