package use_cases.generate_summary_use_case;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * GenerateSummaryID: A class that holds the output data for use by the presenter.
 * @author katarinavucic
 */
public class GenerateSummaryOD {

    private final double monthlyBudget;
    private final Map<String, ArrayList<Double>> statisticalData;
    private JPanel graphPanel;

    /**
     * Creates a new instance of GenerateSummaryOD
     * @param monthlyBudget a double corresponding to the monthlyBudget of this month
     * @param statisticalData a Map containing all the data needed to plot the graphs
     */
    public GenerateSummaryOD(double monthlyBudget, Map<String, ArrayList<Double>> statisticalData) {
        this.monthlyBudget = monthlyBudget;
        this.statisticalData = statisticalData;
    }

    /**
     * Gets the monthlyBudget of this GenerateSummaryOD
     * @return the monthlyBudget of this GenerateSummaryOD
     */
    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    /**
     * Gets the statisticalData of this GenerateSummaryOD
     * @return the statisticalData of this GenerateSummaryOD
     */
    public Map<String, ArrayList<Double>> getStatisticalData() {
        return statisticalData;
    }

    /**
     * Sets the graphPanel of this GenerateSummaryOD
     * @param graphPanel the new budget that this Category will have
     */
    public void setGraphPanel(JPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    /**
     * Gets the graphPanel of this GenerateSummaryOD
     * @return the graphPanel of this GenerateSummaryOD
     */
    public JPanel getGraphPanel(){
        return this.graphPanel;
    }
}
