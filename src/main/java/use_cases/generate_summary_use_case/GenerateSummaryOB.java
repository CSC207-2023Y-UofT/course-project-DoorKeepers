package use_cases.generate_summary_use_case;

/**
 * An interface that the GenerateSummaryP presenter implements to maintain the dependency inversion principle. It
 * contains the method used to format the GenerateSummaryOD into graphs.
 */
public interface GenerateSummaryOB {
    /**
     * Generates a graphical representation of the formatted data associated with the current month and modifies the
     * outputData to contain the resulting JPanel.
     * @param outputData a GenerateSummaryOD object holding the formatted data needed to generate the graphs
     */
    void formatOutputData(GenerateSummaryOD outputData);

}
