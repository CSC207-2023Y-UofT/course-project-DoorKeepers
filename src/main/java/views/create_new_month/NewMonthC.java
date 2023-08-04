package views.create_new_month;

import entities.SessionStorage;
import use_cases.create_new_month.NewMonthIB;
import use_cases.create_new_month.NewMonthID;
import use_cases.create_new_month.NewMonthOD;

public class NewMonthC {
    final NewMonthIB inputBoundary;

    /**
     *
     * @param inputBoundary
     */
    public NewMonthC(NewMonthIB inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * @param session
     * @param monthID
     * @param budgetValue
     * @return
     */
    NewMonthOD getOutput(SessionStorage session, int monthID, double budgetValue) {
        NewMonthID inputData = new NewMonthID(session, monthID, budgetValue);
        return inputBoundary.createOutput(inputData);
    }
}
