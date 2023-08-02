package use_cases.create_new_month;

import entities.SessionStorage;

public class NewMonthID {
    private final SessionStorage session;
    private final int monthID;
    private final int budgetValue;

    /**
     * @param session
     * @param monthID
     * @param budgetValue
     */
    public NewMonthID(SessionStorage session, int monthID, int budgetValue) {
        this.session = session;
        this.monthID = monthID;
        this.budgetValue = budgetValue;
    }

    public SessionStorage getSession() {
        return session;
    }

    public int getMonthID() {
        return monthID;
    }

    public int getBudgetValue() {
        return budgetValue;
    }
}
