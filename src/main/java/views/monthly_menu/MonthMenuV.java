package views.monthly_menu;

import entities.Category;
import entities.Expense;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryIB;
import use_cases.add_edit_category_use_case.CategoryOB;
import use_cases.add_edit_category_use_case.CategoryUCI;
import use_cases.add_edit_expenses_use_case.ExpenseIB;
import use_cases.add_edit_expenses_use_case.ExpenseOB;
import use_cases.add_edit_expenses_use_case.ExpenseUCI;
import use_cases.generate_summary_use_case.GenerateSummaryIB;
import use_cases.generate_summary_use_case.GenerateSummaryOB;
import use_cases.generate_summary_use_case.GenerateSummaryUCI;
import views.add_edit_category_views.AddCategoryV;
import views.add_edit_category_views.CategoryC;
import views.add_edit_category_views.CategoryP;
import views.add_edit_category_views.EditCategoryV;
import views.add_edit_epense_views.AddExpenseV;
import views.add_edit_epense_views.EditExpenseV;
import views.add_edit_epense_views.ExpenseC;
import views.add_edit_epense_views.ExpenseP;
import views.generate_summary_views.GenerateSummaryC;
import views.generate_summary_views.GenerateSummaryP;
import views.generate_summary_views.GenerateSummaryV;

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
    private final UpdateViewC controller;
    private final SessionStorage session;
    private final int monthID;
    private final JFrame frame = new JFrame("Monthly Menu");
    private JButton addExpense;
    private JButton editExpense;
    private JButton addCategory;
    private JButton editCategory;
    private JButton generateSummary;

    /**
     * Construct the view class and call private method to set up GUI.
     * @param controller the controller class to get output data
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     */
    public MonthMenuV(views.monthly_menu.UpdateViewC controller, SessionStorage session, int monthID) {
        this.controller = controller;
        this.session = session;
        this.monthID = monthID;

        // frame initial settings
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        createMonthMenuView();
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
            // Get list of category names
            String[] categoryNames = getCategoryNames();
            // Construct AddExpenseV and show GUI
            ExpenseOB expensePresenter = new ExpenseP();
            ExpenseIB expenseInteractor = new ExpenseUCI(expensePresenter);
            ExpenseC expenseController = new ExpenseC(expenseInteractor);
            AddExpenseV addExpenseView = new AddExpenseV(this,expenseController,categoryNames,monthID,session);
            addExpenseView.openAddExpense();
        } else if (event.getSource()==editExpense) {
            // Get list of category and list of expense names
            String[] categoryNames = getCategoryNames();
            String[] expenseNames = getExpenseNames();
            // Construct EditExpenseV and show GUI
            ExpenseOB expensePresenter = new ExpenseP();
            ExpenseIB expenseInteractor = new ExpenseUCI(expensePresenter);
            ExpenseC expenseController = new ExpenseC(expenseInteractor);
            EditExpenseV editExpenseView = new EditExpenseV(this,expenseController,expenseNames,categoryNames,monthID,session);
            editExpenseView.openEditExpense();
        } else if (event.getSource()==addCategory) {
            // Construct AddCategoryV and show GUI
            CategoryOB categoryPresenter = new CategoryP();
            CategoryIB categoryInteractor = new CategoryUCI(categoryPresenter);
            CategoryC categoryController = new CategoryC(categoryInteractor);
            AddCategoryV addCategoryView = new AddCategoryV(this,categoryController, monthID,session);
            addCategoryView.openAddCategory();
        } else if (event.getSource()==editCategory) {
            // Get list of category names
            String[] categoryNames = getCategoryNames();
            // Construct EditCategoryV and show GUI
            CategoryOB categoryPresenter = new CategoryP();
            CategoryIB categoryInteractor = new CategoryUCI(categoryPresenter);
            CategoryC categoryController = new CategoryC(categoryInteractor);
            EditCategoryV editCategoryView = new EditCategoryV(this,categoryController,categoryNames,
                    monthID,session);
            editCategoryView.openEditCategory();
        } else if (event.getSource()==generateSummary) {
            // Construct GenerateSummaryV and show view
            GenerateSummaryOB genSumPresenter = new GenerateSummaryP();
            GenerateSummaryIB genSumInteractor = new GenerateSummaryUCI(genSumPresenter);
            GenerateSummaryC genSumController = new GenerateSummaryC(genSumInteractor);
            GenerateSummaryV genSumView = new GenerateSummaryV(genSumController, session, monthID);
            genSumView.openSummaryView();
        }
    }

    /**
     * Open Month Menu and notify user if opening Month Menu for a new MonthlyStorage created.
     * @param message notify user if a new MonthlyStorage is created, else null
     * @param loadMonthSaved true if method is called from MainMenuV to load saved MonthlyStorage
     */
    public void openMonthMenu(String message, boolean loadMonthSaved) {
        if (message!=null){ // if opening Month Menu for a newly create MonthlyStorage
            frame.setVisible(true);
            JOptionPane.showMessageDialog(frame,message);
        } else if (loadMonthSaved) { // if opening Month Menu for a saved MonthlyStorage
            frame.setVisible(true);
        } else { // if updating Month Menu after adding/editing Expense/Category
            createMonthMenuView();
            frame.setVisible(true);
        }
    }

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
     */
    private void createMonthMenuView(){
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

        frame.pack();
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

    /**
     * Get Array of String names from ArrayList of Expense.
     * @return String list of names of expenses stored in MonthlyStorage
     */
    private String[] getExpenseNames(){
        ArrayList<Expense> expenses = controller.getOutput(session, monthID).getExpenseData();
        Expense[] expensesArray = new Expense[expenses.size()];
        expenses.toArray(expensesArray);
        String[] expenseNames = new String[expensesArray.length];
        for (int i = 0; i < expensesArray.length; i++){
            Expense expense = expensesArray[i];
            expenseNames[i] = expense.getName();
        }
        return expenseNames;
    }

    /**
     * Get Array of String names from ArrayList of Category.
     * @return String list of names of categories stored in MonthlyStorage
     */
    private String[] getCategoryNames(){
        ArrayList<Category> categories = controller.getOutput(session, monthID).getCategoryData();
        Category[] categoriesArray = new Category[categories.size()];
        categories.toArray(categoriesArray);
        String[] categoryNames = new String[categoriesArray.length];
        for (int i = 0; i < categoriesArray.length; i++){
            Category category = categoriesArray[i];
            categoryNames[i] = category.getName();
        }
        return categoryNames;
    }
}
