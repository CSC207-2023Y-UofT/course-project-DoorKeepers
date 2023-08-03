package use_cases.generate_summary_use_case;

/**
 * An interface that the GenerateSummaryP presenter implements to maintain the dependency inversion principle. It
 * contains the method used to return the GenerateSummaryOD output data.
 */
public interface GenerateSummaryOB {

    /**
     * Returns a GenerateSummaryOD output data holding the remainder of money left in the budget and the statisticalData
     * pertaining to the current month.
     * @param outputData a GenerateSummaryOD object
     */
     GenerateSummaryOD createOutputData(GenerateSummaryOD outputData);


}
