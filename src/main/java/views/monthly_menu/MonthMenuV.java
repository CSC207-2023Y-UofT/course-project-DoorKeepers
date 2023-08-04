package views.monthly_menu;

import entities.Category;
import entities.Expense;
import entities.SessionStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The view class for creating/updating the Month Menu view. This class implements the
 * ActionListener interface. This class calls the controller class to get the
 * MonthMenuOD object, and use the output to set up success and fail view.
 */
public class MonthMenuV implements ActionListener {
    views.monthly_menu.UpdateViewC controller;
    SessionStorage session;
    int monthID;
    JButton addExpense;
    JButton editExpense;
    JButton addCategory;
    JButton editCategory;
    JButton generateSummary;

    /**
     * Set up success and fail view. The success view shows the Month Menu
     * window showing the ID for the month accessed, buttons to add/edit
     * expenses/categories, and the button to generate summary. The Month
     * Menu window also shows the list of expenses and categories currently
     * stored in the MonthlyStorage. The fail view shows a pop-up window
     * showing the error message.
     * Code inspired from
     * <a href="https://stackoverflow.com/questions/5621338/how-to-add-jtable-in-jpanel-with-null-layout">here</a>
     * and <a href="https://youtu.be/S6evF1T_lrU">here</a>.
     * @param controller the controller class to get output data
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     */
    public MonthMenuV(views.monthly_menu.UpdateViewC controller, SessionStorage session, int monthID) {
        this.controller = controller;
        this.session = session;
        this.monthID = monthID;
        //Set the frame
        JFrame frame = new JFrame("Monthly Menu");

        if (controller.getOutput(session, monthID).isSuccessful()){
            JPanel layout = new JPanel(new BorderLayout(20, 20));
            this.addExpense = new JButton("Add an expense");
            this.editExpense = new JButton("Edit an expense");
            this.addCategory = new JButton("Add an category");
            this.editCategory = new JButton("Edit an category");
            this.generateSummary = new JButton("Generate summary");

            //Separate the layout into left and right sides
            JPanel leftLayout = new JPanel(new GridLayout(0,1,100,100));
            leftLayout.setBounds(30,30,200,200);
            JPanel rightLayout = new JPanel(new BorderLayout(20, 20));
            rightLayout.setBounds(30,30,333,200);

            //Left side components: monthID
            JPanel monthPanel = getMonthPanel(monthID);
            leftLayout.add(monthPanel);

            //Left side components: add/edit buttons
            JPanel buttons = new JPanel(new GridLayout(0,2,20,20));
            buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            addExpense.setBounds(200,50,200,30);
            editExpense.setBounds(200,50,200,30);
            addCategory.setBounds(200,50,200,50);
            editCategory.setBounds(200,50,200,50);
            addExpense.addActionListener(this);
            editExpense.addActionListener(this);
            addCategory.addActionListener(this);
            editCategory.addActionListener(this);
            buttons.add(addExpense);
            buttons.add(editExpense);
            buttons.add(addCategory);
            buttons.add(editCategory);
            leftLayout.add(buttons);

            //Left side components: generate summary button
            JPanel genSumButton = new JPanel();
            genSumButton.setLayout(new BoxLayout(genSumButton, BoxLayout.LINE_AXIS));
            genSumButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            generateSummary.setBounds(200,50,200,50);
            generateSummary.addActionListener(this);
            genSumButton.add(generateSummary);
            leftLayout.add(genSumButton);

            //Right side components: set JTables
            ArrayList<Expense> expenses = controller.getOutput(session, monthID).getExpenseData();
            ArrayList<Category> categories = controller.getOutput(session, monthID).getCategoryData();
            Expense[] expensesArray = new Expense[expenses.size()];
            Category[] categoriesArray = new Category[categories.size()];
            expenses.toArray(expensesArray);
            categories.toArray(categoriesArray);
            JTable expenseTable = getExpenseTable(expensesArray);
            JTable categoryTable = getCategoryTable(categoriesArray);
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
        else {
            // Create JPanel for error message
            JPanel layout = new JPanel();
            layout.setLayout(new BoxLayout(layout, BoxLayout.LINE_AXIS));
            layout.add(new JLabel(controller.getOutput(session, monthID).getWarning()));
            layout.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
            frame.add(layout);
        }

        // Allow frame to exit on close for both success and fail cases
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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

    //TODO: implement the reactions to button clicks
    /**
     * React to various button clicks that result in ActionEvent.
     * Code inspired from <a href="https://youtu.be/Kmgo00avvEw?t=2547">here</a>
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==addExpense){
            // call add expense
        } else if (event.getSource()==editExpense) {
            // call edit expense
        } else if (event.getSource()==addCategory) {
            // call add category
        } else if (event.getSource()==editCategory) {
            // call edit category
        } else if (event.getSource()==generateSummary) {
            // call generateSummary
        }
    }
}
