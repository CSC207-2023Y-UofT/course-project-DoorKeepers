package use_cases.create_new_month;

import entities.MonthlyStorage;
import entities.SessionStorage;

public class NewMonthUCI implements NewMonthIB {
    final NewMonthOB outputBoundary;

    /**
     *
     * @param outputBoundary
     */
    public NewMonthUCI(NewMonthOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     *
     * @param input
     * @return
     */
    @Override
    public NewMonthOD createOutput(NewMonthID input) {
        SessionStorage session = input.getSession();
        int monthID = input.getMonthID();
        int budgetValue = input.getBudgetValue();

        MonthlyStorage newMonth = new MonthlyStorage(monthID, budgetValue);
        session.addMonth(newMonth);
        //TODO: create other Category and retrieve recurData
        return outputBoundary.createOutput(new NewMonthOD(session,monthID));
    }
}
