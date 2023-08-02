package use_cases.create_new_month;

import entities.SessionStorage;

public class NewMonthOD {
    SessionStorage session;
    int monthID;
    String warning;
    boolean successful;


    public NewMonthOD(SessionStorage session, int monthID, boolean successful) {
        this.session = session;
        this.monthID = monthID;
        this.successful = successful;
    }

    public NewMonthOD(String warning, boolean successful){
        this.warning = warning;
        this.successful = successful;
    }

    public SessionStorage getSession() {
        return session;
    }

    public int getMonthID() {
        return monthID;
    }

    public String getWarning() {
        return warning;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
