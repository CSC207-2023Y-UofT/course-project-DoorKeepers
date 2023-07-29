package views.monthly_menu;

import entities.Category;
import entities.Expense;
import entities.SessionStorage;

import javax.swing.*;
import java.awt.*;

/**
 * The view class for creating the Month Menu view. This class implements the
 * MonthMenuView interface. This class calls the controller class to get the
 * MonthMenuOD object, and use the output to set up success and fail view.
 */
public class MonthMenuV implements MonthMenuVB {
    views.monthly_menu.UpdateViewC controller;

    /**
     * Set up success and fail view. The success view shows the Month Menu
     * window showing the ID for the month accessed, buttons to add/edit
     * expenses/categories, and the button to generate summary. The Month
     * Menu window also shows the list of expenses and categories currently
     * stored in the MonthlyStorage. The fail view shows a pop-up window
     * showing the error message.
     * @param controller the controller class to get output data
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     */
    public MonthMenuV(views.monthly_menu.UpdateViewC controller, SessionStorage session, int monthID) {
        this.controller = controller;
        //Set the frame
        JFrame frame = new JFrame("Monthly Menu");

        try {
            JPanel layout = new JPanel(new BorderLayout(20, 20));

            //Separate the layout into left and right sides
            JPanel leftLayout = new JPanel(new GridLayout(0,1,100,100));
            leftLayout.setBounds(30,30,200,200);
            JPanel rightLayout = new JPanel(new BorderLayout(20, 20));
            rightLayout.setBounds(30,30,333,200);

            //Left side components
            JPanel monthPanel = getMonthPanel(monthID);
            leftLayout.add(monthPanel);
            JPanel buttons = getButtons();
            leftLayout.add(buttons);
            JPanel genSumButton = getGenSum();
            leftLayout.add(genSumButton);

            //Right side components: set JTables
            Object[] expenseData = controller.getOutput(session, monthID).getExpenseData().toArray();
            Object[] categoryData = controller.getOutput(session, monthID).getCategoryData().toArray();
            JTable expenseTable = getExpenseTable((Expense[]) expenseData);
            JTable categoryTable = getCategoryTable((Category[])categoryData);
            Dimension dimension = new Dimension(333,180);
            expenseTable.setPreferredScrollableViewportSize(dimension);
            categoryTable.setPreferredScrollableViewportSize(dimension);

            //Right side components: set JScrollPanes
            JScrollPane expenseScroll = new JScrollPane(expenseTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            expenseScroll.setBounds(15, 15, 333, 90);
            rightLayout.add(expenseScroll, BorderLayout.NORTH);
            JScrollPane categoryScroll = new JScrollPane(categoryTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            categoryScroll.setBounds(15, 15, 333, 90);
            rightLayout.add(categoryScroll, BorderLayout.CENTER);
            rightLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            //Add both side to overall layout and set up frame
            layout.add(leftLayout, BorderLayout.WEST);
            layout.add(rightLayout, BorderLayout.CENTER);
            frame.setContentPane(layout);
        }
        catch (Exception e){
            // Create JPanel for error message
            JPanel layout = new JPanel();
            layout.setLayout(new BoxLayout(layout, BoxLayout.LINE_AXIS));
            layout.add(new JLabel(controller.getOutput(session, monthID).getWarning()));
            layout.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
            frame.add(layout);
        }
        finally {
            // Allow frame to exit on close for both success and fail cases
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    /**
     * Create the JPanel that shows the monthID.
     * @param monthID the monthID of the required MonthlyStorage
     * @return JPanel containing the monthID
     */
    private static JPanel getMonthPanel(int monthID){
        JPanel monthPanel = new JPanel();
        monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.LINE_AXIS));
        monthPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        monthPanel.add(new JLabel("Month: " + monthID));

        return monthPanel;
    }

    /**
     * Create the JPanel that shows the add/edit expense/category buttons.
     * @return JPanel containing add/edit expense/category buttons
     */
    private static JPanel getButtons() {
        JPanel buttons = new JPanel(new GridLayout(0,2,20,20));

        JButton addExpense = new JButton("Add an expense");
        JButton editExpense = new JButton("Edit an expense");
        JButton addCategory = new JButton("Add an category");
        JButton editCategory = new JButton("Edit an category");

        addExpense.setBounds(200,50,200,30);
        editExpense.setBounds(200,50,200,30);
        addCategory.setBounds(200,50,200,50);
        editCategory.setBounds(200,50,200,50);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttons.add(addExpense);
        buttons.add(editExpense);
        buttons.add(addCategory);
        buttons.add(editCategory);

        return buttons;
    }

    /**
     * Create the JPanel that shows the generate summary button.
     * @return JPanel containing the generate summary button.
     */
    private static JPanel getGenSum(){
        JPanel genSumButton = new JPanel();
        genSumButton.setLayout(new BoxLayout(genSumButton, BoxLayout.LINE_AXIS));
        JButton generateSummary = new JButton("Generate summary");
        generateSummary.setBounds(200,50,200,50);
        genSumButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        genSumButton.add(generateSummary);

        return genSumButton;
    }

    /**
     * Format list of Expense to JTable that shows the expense names and values.
     * @param expenseData expenseData to be outputted
     * @return JTable containing table of expense names and values
     */
    private static JTable getExpenseTable(Expense[] expenseData){
        String[] expenseTableTitle = new String[]{"Expense", "Value"};
        Object[][] expenseList = new Object[expenseData.length][2];
        for (int i = 0; i < expenseData.length; i++) {
            Expense expense = expenseData[i];
            expenseList[i][0] = expense.getName();
            expenseList[i][1] = expense.getValue();
        }
        return new JTable(expenseList, expenseTableTitle);
    }

    /**
     * Format list of Category to JTable that shows the category names and budget values.
     * @param categoryData categoryData to be outputted
     * @return JTable containing table of category names and budget values
     */
    private static JTable getCategoryTable(Category[] categoryData){
        String[] categoryTableTitle = new String[]{"Category", "Budget"};
        Object[][] categoryList = new Object[categoryData.length][2];
        for (int i = 0; i < categoryData.length; i++) {
            Category category = categoryData[i];
            categoryList[i][0] = category.getName();
            categoryList[i][1] = category.getBudget();
        }
        return new JTable(categoryList, categoryTableTitle);
    }

    //TODO: add method for button reactions.
}
