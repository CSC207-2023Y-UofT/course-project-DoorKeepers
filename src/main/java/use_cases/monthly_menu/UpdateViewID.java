package use_cases.monthly_menu;

import entities.SessionStorage;

public class UpdateViewID {
    private SessionStorage session;
    private int monthID;

    public UpdateViewID(SessionStorage session, int monthID){
        this.session = session;
        this.monthID = monthID;
    }

    public SessionStorage getSession() {
        return session;
    }

    public int getMonthID() {
        return monthID;
    }

    public void setSession(SessionStorage session) {
        this.session = session;
    }

    public void setMonthID(int monthID) {
        this.monthID = monthID;
    }
}
