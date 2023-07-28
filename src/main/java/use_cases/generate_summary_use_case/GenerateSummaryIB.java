package use_cases.generate_summary_use_case;

import entities.EntityException;

/**
 * GenerateSummaryIB: An interface that the interactor implements to maintain the dependency inversion principle
 * @author katarinavucic
 */
public interface GenerateSummaryIB {
    /**
     * Generates a graphical representation of the MonthlyStorage data associated with monthID in the currentSession
     * @param inputData a GenerateSummaryID object holding the SessionStorage and monthID needed to retrieve data
     * @return a JPanel holding the graphs
     */
    public GenerateSummaryOD generateNewSummary(GenerateSummaryID inputData) throws EntityException;
}
