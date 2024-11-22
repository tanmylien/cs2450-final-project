package model;

public class Product {
    private String name;
    private double price;
    private String imagePath;
    private String occasion;
    private String size;

    public Product(String name, double price, String imagePath, String occasion, String size) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.occasion = occasion;
        this.size = size;
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }
    public String getOccasion() { return occasion; }
    public String getSize() { return size; }

    // Additional methods, if necessary
}

