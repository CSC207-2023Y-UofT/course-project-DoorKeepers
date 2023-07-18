package entities;

public class Expense {
    private String name;
    private Category category;
    private double value;

    /**
     * Creates a new Expense with the given data
     * @param name: name of expense
     * @param category: category of expense
     * @param value: value (price) of expense
     */
    public Expense(String name, Category category, double value){
        this.name = name;
        this.category = category;
        this.value = value;
    }

    public void setName(String new_name){
        this.name = new_name;
    }

    public void setCategory(Category new_category){
        this.category = new_category;
    }

    public void setValue(double new_value){
        this.value = new_value;
    }

    public String getName(){
        return this.name;
    }

    public Category getCategory(){
        return this.category;
    }

    public double getValue(){
        return this.value;
    }

}
