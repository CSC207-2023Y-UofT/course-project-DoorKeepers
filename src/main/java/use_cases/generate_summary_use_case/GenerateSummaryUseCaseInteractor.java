package use_cases.generate_summary_use_case;

import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;

/**
 * A class that calls GenerateSummaryPresenter presenter and GenerateSummaryUseCaseInterpreter interpreter to execute the steps in
 * generating the information for the graphical summary. It returns a GenerateSummaryOutputData object containing information
 * needed to generate the graphs.
 */
public class GenerateSummaryUseCaseInteractor implements GenerateSummaryInputBoundary {

    private final GenerateSummaryOutputBoundary presenter;

    /**
     * Creates a new GenerateSummaryUseCaseInteractor with the provided presenter.
     * @param presenter instance of presenter that will be called in the generateNewSummary method
     */
    public GenerateSummaryUseCaseInteractor(GenerateSummaryOutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * Generates the information needed for the graphical representation of the MonthlyStorage data associated with
     * monthID in the currentSession.
     * @param inputData a GenerateSummaryInputData object holding the SessionStorage and monthID needed to retrieve data
     * @return a GenerateSummaryOutputData object holding the information needed to plot the graphs.
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     * raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     * SessionStorage, and is not something the user can fix.
     */
    @Override
    public GenerateSummaryOutputData generateNewSummary(GenerateSummaryInputData inputData) throws EntityException{
        SessionStorage currentSession = inputData.getCurrentSession();
        MonthlyStorage monthlyStorage = currentSession.getMonthlyData(inputData.getMonthID());
        GenerateSummaryUseCaseInterpreter interpreter = new GenerateSummaryUseCaseInterpreter(monthlyStorage);
        GenerateSummaryOutputData outputData = new GenerateSummaryOutputData(interpreter.getRemainder(),
                interpreter.getStatisticalData());
        return presenter.createOutputData(outputData);
    }

}
