package use_cases.create_new_month;

import entities.*;
import use_cases.monthly_menu.MonthMenuOD;

import java.util.ArrayList;

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

        try {
            ArrayList<Expense> recurData= session.getRecurData();
            MonthlyStorage newMonth = new MonthlyStorage(monthID, budgetValue);
            Category other = newMonth.getCategoryData().get(0);
            for (Expense expense : recurData){
                newMonth.addExpense(new Expense(expense.getName(),other, expense.getValue()));
            }
            session.addMonth(newMonth);
            return outputBoundary.createOutput(new NewMonthOD(session,monthID,true));
        } catch (EntityException e){
            return outputBoundary.createOutput(new NewMonthOD(
                    "Something went wrong, please try again.",false));
        }
    }
}
