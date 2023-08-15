package views.generate_summary_views;

import use_cases.generate_summary_use_case.GenerateSummaryOutputBoundary;
import use_cases.generate_summary_use_case.GenerateSummaryOutputData;

/**
 * A class that returns the GenerateSummaryOutputData output data given the remainder and statistical data.
 */
public class GenerateSummaryPresenter implements GenerateSummaryOutputBoundary {

    /**
     * Returns a GenerateSummaryOutputData output data holding the remainder of money left in the budget and the statisticalData
     * pertaining to the current month.
     * @param outputData a GenerateSummaryOutputData object
     */
    @Override
    public GenerateSummaryOutputData createOutputData(GenerateSummaryOutputData outputData) {
        return outputData;
    }
}
