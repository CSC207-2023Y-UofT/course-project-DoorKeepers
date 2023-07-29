package views.generate_summary_views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import use_cases.generate_summary_use_case.GenerateSummaryOB;
import use_cases.generate_summary_use_case.GenerateSummaryOD;

import java.util.ArrayList;
import java.util.Map;

/**
 * GenerateSummaryP: A class that generates the graphs
 * Code for Bar Graph inspired by http://www.java2s.com/Code/Java/Chart/JFreeChartStackedBarChartDemo4.htm
 * Code for Pie Chart inspired by ...
 * @author katarinavucic
 */
public class GenerateSummaryP extends JFrame implements GenerateSummaryOB {
    private double monthlybudget;
    private Map<String, ArrayList<Double>> statisticalData;

    /**
     * Generates a graphical representation of the formatted data associated with the current month and modifies the
     * outputData to contain the resulting JPanel.
     * @param outputData a GenerateSummaryOD object holding the formatted data needed to generate the graphs
     */
    @Override
    public void formatOutputData(GenerateSummaryOD outputData) {
        this.monthlybudget = outputData.getMonthlyBudget();
        this.statisticalData = outputData.getStatisticalData();

        outputData.setGraphPanel(generateBarGraph());
    }

    /**
     * Generates the Bar Graph
     * @return a JPanel object holding the Bar Graph
     */
    private JPanel generateBarGraph() {
        CategoryDataset barGraphDataset = createBarGraphDataset();
        JFreeChart barGraph = ChartFactory.createStackedBarChart("Monthly Expenses", "Category", "Value", barGraphDataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot categoryPlot = barGraph.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setDrawBarOutline(false);
        return new ChartPanel(barGraph);
    }

    /**
     * Generates the dataset used to plot the Bar Graph
     * @return a CategoryDataset object holding the data
     */
    private CategoryDataset createBarGraphDataset() {
        DefaultCategoryDataset barGraphDataset = new DefaultCategoryDataset();
        for (String c: this.statisticalData.keySet()){
            barGraphDataset.addValue(this.statisticalData.get(c).get(0), "Spent", c);
            barGraphDataset.addValue(this.statisticalData.get(c).get(1), "Budget", c);
        }
        return barGraphDataset;
    }

}
