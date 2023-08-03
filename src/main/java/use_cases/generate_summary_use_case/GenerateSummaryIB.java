package use_cases.generate_summary_use_case;

import entities.EntityException;

/**
 * An interface that the GenerateSummaryUCI interactor implements to maintain the dependency inversion principle. It
 * contains the method used to generate a new graphical summary.
 */
public interface GenerateSummaryIB {

    /**
     * Generates a graphical representation of the MonthlyStorage data associated with monthID in the currentSession.
     * @param inputData a GenerateSummaryID object holding the SessionStorage and monthID needed to retrieve data
     * @return a JPanel holding the graphs
     */
     GenerateSummaryOD generateNewSummary(GenerateSummaryID inputData) throws EntityException;
}
