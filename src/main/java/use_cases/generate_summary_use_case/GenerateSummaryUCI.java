package use_cases.generate_summary_use_case;

import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;

/**
 * A class that calls GenerateSummaryP presenter and GenerateSummaryUCInterpreter interpreter to execute the steps in
 * generating the information for the graphical summary. It returns a GenerateSummaryOD object containing information
 * needed to generate the graphs.
 */
public class GenerateSummaryUCI implements GenerateSummaryIB {

    private final GenerateSummaryOB presenter;

    /**
     * Creates a new GenerateSummaryUCI with the provided presenter.
     * @param presenter instance of presenter that will be called in the generateNewSummary method
     */
    public GenerateSummaryUCI(GenerateSummaryOB presenter){
        this.presenter = presenter;
    }

    /**
     * Generates the information needed for the graphical representation of the MonthlyStorage data associated with
     * monthID in the currentSession.
     * @param inputData a GenerateSummaryID object holding the SessionStorage and monthID needed to retrieve data
     * @return a GenerateSummaryOD object holding the information needed to plot the graphs.
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     * raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     * SessionStorage, and is not something the user can fix.
     */
    @Override
    public GenerateSummaryOD generateNewSummary(GenerateSummaryID inputData) throws EntityException{
        SessionStorage currentSession = inputData.getCurrentSession();
        MonthlyStorage monthlyStorage = currentSession.getMonthlyData(inputData.getMonthID());
        GenerateSummaryUCInterpreter interpreter = new GenerateSummaryUCInterpreter(monthlyStorage);
        GenerateSummaryOD outputData = new GenerateSummaryOD(interpreter.getRemainder(),
                interpreter.getStatisticalData());
        return presenter.createOutputData(outputData);
    }

}
