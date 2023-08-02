package use_cases.generate_summary_use_case;


import javax.swing.*;
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
    private JPanel barGraphPanel;
    private JPanel pieChartPanel;

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

    /**
     * Sets the barGraphPanel of this GenerateSummaryOD.
     * @param barGraphPanel the new barGraphPanel
     */
    public void setBarGraphPanel(JPanel barGraphPanel) {
        this.barGraphPanel = barGraphPanel;
    }

    /**
     * Sets the pieChartPanel of this GenerateSummaryOD.
     * @param pieChartPanel the new pieChartPanel
     */
    public void setPieChartPanel(JPanel pieChartPanel) {
        this.pieChartPanel = pieChartPanel;
    }

    /**
     * Gets the BarGraphPanel of this GenerateSummaryOD.
     * @return the BarGraphPanel of this GenerateSummaryOD
     */
    public JPanel getBarGraphPanel(){
        return this.barGraphPanel;
    }

    /**
     * Gets the pieChartPanel of this GenerateSummaryOD.
     * @return the pieChartPanel of this GenerateSummaryOD
     */
    public JPanel getPieChartPanel() {
        return pieChartPanel;
    }

}
