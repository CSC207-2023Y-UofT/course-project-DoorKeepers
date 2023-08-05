package views.generate_summary_views;

import entities.EntityException;
import entities.SessionStorage;
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
import use_cases.generate_summary_use_case.GenerateSummaryOD;

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

    /**
     * Creates a new GenerateSummaryV view, containing an empty screen and calls the controller to retrieve the
     * formatted data. Then, it generates a graphical representation of the formatted data associated with the current
     * month and displays them to the user. If an EntityException is caught, it will display an error to the user.
     * @param controller a GenerateSummaryC controller that will generate the formatted data needed to generate
     * the graphs
     * @param session a SessionStorage object that contains the data of the current session
     * @param monthID an int corresponding to the current MonthlyStorage month
     */
    public GenerateSummaryV(GenerateSummaryC controller, SessionStorage session, int monthID){
        JFrame screen = new JFrame("Graphical Summary");

        try {
            GenerateSummaryOD outputData = controller.generate(session, monthID);
            double remainder = outputData.getRemainder();
            Map<String, ArrayList<Double>> statisticalData = outputData.getStatisticalData();

            JPanel graphs = new JPanel();
            graphs.setLayout(new BoxLayout(graphs, BoxLayout.X_AXIS));
            screen.add(graphs);
            graphs.add(generateBarGraph(statisticalData));
            graphs.add(generatePieChart(remainder, statisticalData));
        } catch (EntityException e) {
            JPanel errorMessage = new JPanel();
            errorMessage.setLayout(new BoxLayout(errorMessage, BoxLayout.X_AXIS));
            errorMessage.add(new JLabel("An error has occurred. Please reload the program."));
            screen.add(errorMessage);
        }
        screen.pack();
        screen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        screen.setSize(800, 400);
        screen.setVisible(true);
    }

    /**
     * Generates the Bar Graph.
     * @param statisticalData a Map containing all the data needed to plot the graphs
     * @return a JPanel object holding the Bar Graph
     */
    private JPanel generateBarGraph(Map<String, ArrayList<Double>> statisticalData) {
        CategoryDataset barGraphDataset = createBarGraphDataset(statisticalData);
        JFreeChart barGraph = ChartFactory.createStackedBarChart("Monthly Expenses", "Category",
                "Value", barGraphDataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot categoryPlot = barGraph.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setDrawBarOutline(false);
        return new ChartPanel(barGraph);
    }

    /**
     * Generates the Pie Chart.
     * @param remainder a double corresponding to the money unspent in this month
     * @param statisticalData a Map containing all the data needed to plot the graphs
     * @return a JPanel object holding the Pie Chart
     */
    private JPanel generatePieChart(double remainder, Map<String, ArrayList<Double>> statisticalData) {
        PieDataset<String> pieChartDataset = createPieChartDataset(remainder, statisticalData);
        JFreeChart pieChart = ChartFactory.createPieChart("Monthly Expenses", pieChartDataset, true,
                true, false);
        Title subtitle;
        if (remainder >= 0){
            subtitle = new TextTitle("You are within budget.");
        } else {
            subtitle = new TextTitle("You are over budget.");
        }
        pieChart.addSubtitle(subtitle);
        return new ChartPanel(pieChart);
    }

    /**
     * Generates the dataset used to plot the Bar Graph.
     * @param statisticalData a Map containing all the data needed to plot the graphs
     * @return a CategoryDataset object holding the data
     */
    private CategoryDataset createBarGraphDataset(Map<String, ArrayList<Double>> statisticalData) {
        DefaultCategoryDataset barGraphDataset = new DefaultCategoryDataset();
        for (String c: statisticalData.keySet()){
            barGraphDataset.addValue(statisticalData.get(c).get(0), "Spent", c);
            barGraphDataset.addValue(statisticalData.get(c).get(1), "Budget", c);
        }
        return barGraphDataset;
    }

    /**
     * Generates the dataset used to plot the Pie Chart.
     * @param remainder a double corresponding to the money unspent in this month
     * @param statisticalData a Map containing all the data needed to plot the graphs
     * @return a PieDataset object holding the data
     */
    private PieDataset<String> createPieChartDataset(double remainder, Map<String, ArrayList<Double>> statisticalData) {
        DefaultPieDataset<String> pieChartDataset = new DefaultPieDataset<>();
        for (String c: statisticalData.keySet()){
            pieChartDataset.setValue(c, statisticalData.get(c).get(0));
        }
        if (remainder >= 0) {
            pieChartDataset.setValue("Unspent", remainder);
        }
        return pieChartDataset;
    }
}
