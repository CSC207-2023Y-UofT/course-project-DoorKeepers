package use_cases.create_new_month;

import entities.SessionStorage;

/**
 * The input data class for storing Create New Month use case inputs.
 * The controller class of Create New Month use case creates an object
 * of this class, and pass it to the interactor class.
 */
public class NewMonthInputData {
    private final SessionStorage session;
    private final int monthID;
    private final double budgetValue;

    /**
     * Constructs a NewMonthInputData holding input data.
     * @param session the SessionStorage to store the new MonthlyStorage
     * @param monthID the monthID of the new MonthlyStorage
     * @param budgetValue the budget for the new MonthlyStorage
     */
    public NewMonthInputData(SessionStorage session, int monthID, double budgetValue) {
        this.session = session;
        this.monthID = monthID;
        this.budgetValue = budgetValue;
    }

    /**
     * Gets the session stored.
     * @return SessionStorage accessed in interactor
     */
    public SessionStorage getSession() {
        return session;
    }

    /**
     * Gets the monthID stored.
     * @return int ID of new MonthlyStorage to be created in interactor
     */
    public int getMonthID() {
        return monthID;
    }

    /**
     * Gets the budget value stored.
     *
     * @return double type budget for new MonthlyStorage to be created in interactor
     */
    public double getBudgetValue() {
        return budgetValue;
    }
}
