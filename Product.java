package model;

public class Product {
    private String name;
    private double price;
    private String imagePath;
    private String category;  // Replaced occasion with category

    public Product(String name, double price, String imagePath, String category) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;  // Updated to category
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }
    public String getCategory() { return category; }  // Updated to get category

    // Additional methods, if necessary
}

