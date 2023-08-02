package use_cases.create_new_month;

import entities.SessionStorage;

public class NewMonthOD {
    private SessionStorage session;
    private int monthID;

    public NewMonthOD(SessionStorage session, int monthID) {
        this.session = session;
        this.monthID = monthID;
    }

    public SessionStorage getSession() {
        return session;
    }

    public int getMonthID() {
        return monthID;
    }
}
