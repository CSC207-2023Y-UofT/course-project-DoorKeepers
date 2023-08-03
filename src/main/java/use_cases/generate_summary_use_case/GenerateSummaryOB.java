package use_cases.generate_summary_use_case;

import java.util.ArrayList;
import java.util.Map;

/**
 * An interface that the GenerateSummaryP presenter implements to maintain the dependency inversion principle. It
 * contains the method used to create the GenerateSummaryOD output data.
 */
public interface GenerateSummaryOB {
    /**
     * Creates a GenerateSummaryOD output data holding the remainder of money left in the budget and the statisticalData
     * pertaining to the current month.
     * @param remainder a double representing the money that the user has not spent in their budget
     * @param statisticalData a map containing String names of Category objects as the keys and doubles representing
     *                        money spent and budget as the values
     */
     GenerateSummaryOD createOutputData(double remainder, Map<String, ArrayList<Double>> statisticalData);

}
