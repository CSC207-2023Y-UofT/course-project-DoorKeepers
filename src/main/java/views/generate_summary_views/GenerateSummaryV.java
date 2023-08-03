package views.generate_summary_views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * A class that displays and generates the Bar Graph and Pie Chart given the remainder and statistical data. It also
 * has a method to display an error message.
 * Used <a href="https://github.com/jfree/jfreechart">JFree Chart</a> package for graphing.
 * Code for Bar Graph inspired by
 * <a href="http://www.java2s.com/Code/Java/Chart/JFreeChartStackedBarChartDemo4.htm">this website.</a>
 * Code for Pie Chart inspired by
 * <a href="https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm">this website.</a>
 */
public class GenerateSummaryV extends JFrame {
    private final JFrame screen;
    private double remainder;
    private Map<String, ArrayList<Double>> statisticalData;

    /**
     * Creates a new GenerateSummaryV, containing an empty screen.
     */
    public GenerateSummaryV(){
        screen = new JFrame("Graphical Summary");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(800, 400);
    }

    /**
     * Generates a graphical representation of the formatted data associated with the current month and displays them to
     * the user
     * @param remainder a double representing the money that the user has not spent in their budget
     * @param statisticalData a map containing String names of Category objects as the keys and doubles representing
     *                        money spent and budget as the values
     */
    public void displayGraphs(double remainder, Map<String, ArrayList<Double>> statisticalData){
        this.remainder = remainder;
        this.statisticalData = statisticalData;

        JPanel graphs = new JPanel();
        graphs.setLayout(new BoxLayout(graphs, BoxLayout.X_AXIS));
        screen.add(graphs);
        graphs.add(generateBarGraph());
        graphs.add(generatePieChart());
        screen.setVisible(true);
    }

    /**
     * Displays an error message to the user.
     * @param message an error message
     */
    public void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(screen, message);
        screen.setVisible(true);
    }

    /**
     * Generates the Bar Graph.
     * @return a JPanel object holding the Bar Graph
     */
    private JPanel generateBarGraph() {
        CategoryDataset barGraphDataset = createBarGraphDataset();
        JFreeChart barGraph = ChartFactory.createStackedBarChart("Monthly Expenses", "Category",
                "Value", barGraphDataset, PlotOrientation.VERTICAL, true, true, false);
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
        PieDataset<String> pieChartDataset = createPieChartDataset();
        JFreeChart pieChart = ChartFactory.createPieChart("Monthly Expenses", pieChartDataset, true,
                true, false);
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
    private PieDataset<String> createPieChartDataset() {
        DefaultPieDataset<String> pieChartDataset = new DefaultPieDataset<>();
        for (String c: this.statisticalData.keySet()){
            pieChartDataset.setValue(c, this.statisticalData.get(c).get(0));
        }
        if (this.remainder >= 0) {
            pieChartDataset.setValue("Unspent", this.remainder);
        }
        return pieChartDataset;
    }
}
