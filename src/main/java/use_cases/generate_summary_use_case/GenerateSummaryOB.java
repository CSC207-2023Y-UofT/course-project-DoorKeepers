package use_cases.generate_summary_use_case;

/**
 * GenerateSummaryOB: An interface that the presenter implements to maintain the dependency inversion principle
 * @author katarinavucic
 */
public interface GenerateSummaryOB {
    /**
     * Generates a graphical representation of the formatted data associated with the current month and modifies the
     * outputData to contain the resulting JPanel.
     * @param outputData a GenerateSummaryOD object holding the formatted data needed to generate the graphs
     */
    void formatOutputData(GenerateSummaryOD outputData);

}
