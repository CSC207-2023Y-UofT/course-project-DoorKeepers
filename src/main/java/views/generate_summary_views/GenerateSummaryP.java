package views.generate_summary_views;

import use_cases.generate_summary_use_case.GenerateSummaryOB;
import use_cases.generate_summary_use_case.GenerateSummaryOD;

/**
 * A class that returns the GenerateSummaryOD output data given the remainder and statistical data.
 */
public class GenerateSummaryP implements GenerateSummaryOB {

    /**
     * Returns a GenerateSummaryOD output data holding the remainder of money left in the budget and the statisticalData
     * pertaining to the current month.
     * @param outputData a GenerateSummaryOD object
     */
    @Override
    public GenerateSummaryOD createOutputData(GenerateSummaryOD outputData) {
        return outputData;
    }
}
