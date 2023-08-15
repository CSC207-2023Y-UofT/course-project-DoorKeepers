package use_cases.generate_summary_use_case;


import java.util.ArrayList;
import java.util.Map;

/**
 * A class that holds the output data for use by the GenerateSummaryView. This includes a remainder and the statistical
 * data used to generate graphs.
 */
public class GenerateSummaryOutputData {

    private final double remainder;
    private final Map<String, ArrayList<Double>> statisticalData;

    /**
     * Creates a new instance of GenerateSummaryOutputData.
     * @param remainder a double corresponding to the money unspent in this month
     * @param statisticalData a Map containing all the data needed to plot the graphs
     */
    public GenerateSummaryOutputData(double remainder, Map<String, ArrayList<Double>> statisticalData) {
        this.remainder = remainder;
        this.statisticalData = statisticalData;
    }

    /**
     * Gets the remainder of this GenerateSummaryOutputData.
     * @return the remainder of this GenerateSummaryOutputData
     */
    public double getRemainder() {
        return remainder;
    }

    /**
     * Gets the statisticalData of this GenerateSummaryOutputData.
     * @return the statisticalData of this GenerateSummaryOutputData
     */
    public Map<String, ArrayList<Double>> getStatisticalData() {
        return statisticalData;
    }
}
