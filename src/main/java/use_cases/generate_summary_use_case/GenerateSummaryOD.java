package use_cases.generate_summary_use_case;


import java.util.ArrayList;
import java.util.Map;

/**
 * A class that holds the output data for use by the GenerateSummaryP. This includes a remainder, the statistical data
 * used to generate graphs, as well as the bar graph and pie chart panels that will be generated after the object is
 * passed to the GenerateSummaryP.
 */
public class GenerateSummaryOD {

    private final double remainder;
    private final Map<String, ArrayList<Double>> statisticalData;

    /**
     * Creates a new instance of GenerateSummaryOD.
     * @param remainder a double corresponding to the money unspent in this month
     * @param statisticalData a Map containing all the data needed to plot the graphs
     */
    public GenerateSummaryOD(double remainder, Map<String, ArrayList<Double>> statisticalData) {
        this.remainder = remainder;
        this.statisticalData = statisticalData;
    }

    /**
     * Gets the remainder of this GenerateSummaryOD.
     * @return the remainder of this GenerateSummaryOD
     */
    public double getRemainder() {
        return remainder;
    }

    /**
     * Gets the statisticalData of this GenerateSummaryOD.
     * @return the statisticalData of this GenerateSummaryOD
     */
    public Map<String, ArrayList<Double>> getStatisticalData() {
        return statisticalData;
    }
}
