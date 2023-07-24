package use_cases.monthly_menu;

public class MonthMenuOD {
    Object[][] expenseList;
    Object[][] categoryList;

    public MonthMenuOD(Object[][] expenses, Object[][] categories){
        this.expenseList = expenses;
        this.categoryList = categories;
    }

    public Object[][] getExpenseList() {
        return expenseList;
    }

    public Object[][] getCategoryList() {
        return categoryList;
    }

    public void setExpenseList(Object[][] expenseList) {
        this.expenseList = expenseList;
    }

    public void setCategoryList(Object[][] categoryList) {
        this.categoryList = categoryList;
    }
}
