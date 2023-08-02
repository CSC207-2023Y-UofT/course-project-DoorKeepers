package use_cases.generate_summary_use_case;

import entities.EntityException;
import entities.MonthlyStorage;
import entities.SessionStorage;

/**
 * A class that calls GenerateSummaryP presenter and GenerateSummaryUCInterpreter interpreter to execute the steps in
 * generating the graphical summary. It returns a GenerateSummaryOD object containing JPanels with the Bar Graph and
 * Pie Chart.
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
     * Generates a graphical representation of the MonthlyStorage data associated with monthID in the currentSession.
     * @param inputData a GenerateSummaryID object holding the SessionStorage and monthID needed to retrieve data
     * @return a JPanel holding the graphs
     */
    @Override
    public GenerateSummaryOD generateNewSummary(GenerateSummaryID inputData) throws EntityException{
        SessionStorage currentSession = inputData.getCurrentSession();
        MonthlyStorage monthlyStorage = currentSession.getMonthlyData(inputData.getMonthID());
        GenerateSummaryUCInterpreter interpreter = new GenerateSummaryUCInterpreter(monthlyStorage);
        GenerateSummaryOD outputData = new GenerateSummaryOD(interpreter.getRemainder(), interpreter.getStatisticalData());
        presenter.formatOutputData(outputData);
        return outputData;
    }

}
