package views.load_monthly_menu;

import entities.SessionStorage;

public interface LoadMonthMenuVB {
    /**
     * Load Month Menu and notify user.
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     */
    void loadMonthMenu(SessionStorage session, int monthID);
}
