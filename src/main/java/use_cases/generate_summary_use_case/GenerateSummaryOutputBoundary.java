package use_cases.generate_summary_use_case;

/**
 * An interface that the GenerateSummaryPresenter presenter implements to maintain the dependency inversion principle. It
 * contains the method used to return the GenerateSummaryOutputData output data.
 */
public interface GenerateSummaryOutputBoundary {

    /**
     * Returns a GenerateSummaryOutputData output data holding the remainder of money left in the budget and the statisticalData
     * pertaining to the current month.
     * @param outputData a GenerateSummaryOutputData object
     * @return the GenerateSummaryOutputData object with statistical data and remainder information
     */
     GenerateSummaryOutputData createOutputData(GenerateSummaryOutputData outputData);


}
