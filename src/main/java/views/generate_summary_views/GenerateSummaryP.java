package views.generate_summary_views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import use_cases.generate_summary_use_case.GenerateSummaryOB;
import use_cases.generate_summary_use_case.GenerateSummaryOD;

import java.util.ArrayList;
import java.util.Map;

/**
 * A class that generates the Bar Graph and Pie Chart given the GenerateSummaryOD outputData.
 * Code for Bar Graph inspired by <a href="http://www.java2s.com/Code/Java/Chart/JFreeChartStackedBarChartDemo4.htm">this website.</a>
 * Code for Pie Chart inspired by <a href="https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm">this website.</a>
 */
public class GenerateSummaryP extends JFrame implements GenerateSummaryOB {
    private double remainder;
    private Map<String, ArrayList<Double>> statisticalData;

    /**
     * Generates a graphical representation of the formatted data associated with the current month and modifies the
     * outputData to contain the resulting JPanels.
     * @param outputData a GenerateSummaryOD object holding the formatted data needed to generate the graphs
     */
    @Override
    public void formatOutputData(GenerateSummaryOD outputData) {
        this.remainder = outputData.getRemainder();
        this.statisticalData = outputData.getStatisticalData();
        outputData.setBarGraphPanel(generateBarGraph());
        outputData.setPieChartPanel(generatePieChart());
    }

    /**
     * Generates the Bar Graph.
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
     * Generates the Pie Chart.
     * @return a JPanel object holding the Pie Chart
     */
    private JPanel generatePieChart() {
        PieDataset pieChartDataset = createPieChartDataset();
        JFreeChart pieChart = ChartFactory.createPieChart("Monthly Expenses", pieChartDataset, true, true, false);
        Title subtitle;
        if (this.remainder >= 0){
            subtitle = new TextTitle("You are within budget.");
        } else {
            subtitle = new TextTitle("You are over budget.");
        }
        pieChart.addSubtitle(subtitle);
        return new ChartPanel(pieChart);
    }

    /**
     * Generates the dataset used to plot the Bar Graph.
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

    /**
     * Generates the dataset used to plot the Pie Chart.
     * @return a PieDataset object holding the data
     */
    private PieDataset createPieChartDataset() {
        DefaultPieDataset pieChartDataset = new DefaultPieDataset();
        for (String c: this.statisticalData.keySet()){
            pieChartDataset.setValue(c, this.statisticalData.get(c).get(0));
        }
        if (this.remainder >= 0) {
            pieChartDataset.setValue("Unspent", this.remainder);
        }
        return pieChartDataset;
    }

}
