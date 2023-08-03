package views.generate_summary_views;

import use_cases.generate_summary_use_case.GenerateSummaryOB;
import use_cases.generate_summary_use_case.GenerateSummaryOD;

import java.util.ArrayList;
import java.util.Map;

/**
 * A class that creates the GenerateSummaryOD output data given the remainder and statistical data.
 */
public class GenerateSummaryP implements GenerateSummaryOB {

    /**
     * Creates a GenerateSummaryOD output data holding the remainder of money left in the budget and the statisticalData
     * pertaining to the current month.
     * @param remainder a double representing the money that the user has not spent in their budget
     * @param statisticalData a map containing String names of Category objects as the keys and doubles representing
     *                        money spent and budget as the values
     */
    @Override
    public GenerateSummaryOD createOutputData(double remainder, Map<String, ArrayList<Double>> statisticalData) {
        return new GenerateSummaryOD(remainder, statisticalData);
    }
}
