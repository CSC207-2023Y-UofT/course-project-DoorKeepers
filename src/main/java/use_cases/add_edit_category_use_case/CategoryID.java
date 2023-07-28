package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.SessionStorage;

public class CategoryID {
    private String name;
    private Object value;
    private final int monthID;

    private final SessionStorage session;

    public CategoryID(String name, Object value, int monthID, SessionStorage session){
        this.name = name;
        this.value = value;
        this.monthID = monthID;
        this.session = session;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public Object getValue(){return value;}
    public void setValue(double value){this.value = value;}

    public int getMonthID(){return monthID;}
    public SessionStorage getSession(){return session;}

}
