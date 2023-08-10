package entities;

public class CategoryFactory {
    public Category create(String name, double value){return new Category(name, value);}
}
