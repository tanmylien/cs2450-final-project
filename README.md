# Cupcake Shop GUI Group #10

## 👤 Group members: Aaron Aramburo, Natasha Budiman, Jennifer Pedomoro, Jennifer Garcia, My Lien Tan

## 📜 Table of Contents
- [Purpose]
- [Screenshots](#-screenshots)
- [Goal](#-goal)
- [Tools Used](#-toolsused)
- [Features](#feature)
- [Code functions](#codefunctions)

## ✨ Purpose
Our online cupcake store is designed to make it easy and fun for customers to browse and order cupcakes from the comfort of their homes. Whether you're looking for a cupcake for a special occasion or just to treat yourself, we have something for everyone!

## 🎯 Goal
Our goal is to make buying cupcakes online a simple, enjoyable, and seamless experience. With easy-to-use features like a search bar, filters, a shopping cart, and secure checkout, we want customers to have a smooth and satisfying shopping experience from start to finish.
    - Search & Filter
    - Cart & Checkout
    - Real-time Price Updates
    - Easy Checkout

## 🔧 Tools Used
    - Java Swing: For GUI components and layout.
    - Cart and CartItem Class: Handle cart functionality and provides the total price of the order.
    - Product Class: Handles each product functionality (Name, Price, ImagePath, Category) and provide description of each product.
    - LocalDate: To calculate and format future pickup dates.
    - AWT and Swing Libraries: For styling, event handling, and layout management.
## 📌 Features
### 🛍️ Product Catalog
**Browse Cupcakes**: View a wide variety of delicious cupcakes with vibrant images and detailed pricing.

**Categories**: Filter cupcakes by categories like Signature Flavors, Seasonal Specials, and Price Range.

**Search Bar**: Quickly find your favorite cupcake by typing in its name.
### 🛒 Shopping Cart
**Add to Cart**: Seamlessly add cupcakes to your cart with just one click.

**Modify Quantities**: Adjust the quantity of each item directly within the cart.

**Remove Items**: Easily remove items from your cart to update your order.
### 📦 Checkout
**User Details**: Input your name, address, and contact number for smooth order processing.

**Order Options**: Select your preferred pickup time, including an option to schedule future pickups.
### 🎨 Modern Design
**Intuitive Layout**: A clean, visually pleasing design with pastel colors that enhance the user experience.

**Responsive UI**: Adapted for desktop and mobile platforms to ensure accessibility.
### 🔍 Additional Features
**Price Filters**: Filter products by price to suit your budget.

**Live Total Calculation**: Automatically update the total price as items are added or removed from the cart.

## 📷 Screenshots
    ![Screenshot 2024-12-08 222327](https://github.com/user-attachments/assets/321cf018-cc3f-4fcc-9ff0-682d6e78ca6e)
    ![Screenshot 2024-12-08 222327](https://github.com/user-attachments/assets/4dcbf4c3-6026-4469-be83-af6763e3cbdf)





# Code Functions
#### createTopPanel() : Create top panel (set backgorunds, labels, and create Cart Panel)
#### createCartPanel() : Create Cart Panel (set backgrounds, labels, buttons)
#### createSearchPanel() : Create Search Panel (set backgrounds, search field, buttons)
#### performSearch() : Function to perform search feature 
#### addFilterPanel(): Add Filter Panel to the left side of the window
#### createFilterPanel(): Create Filter Panel to add filter components (categories, clear filter button)
#### createPriceSlider(): Create Price slider (set major and paint ticks, set paint labels)
#### createfilterCategory():  Add filtered products based on the category selected
#### filterProductsBySlider(): Set filtered products by slider
#### resetFilters(): Clear all filters
#### createCustomPriceLabels(): Create price labels
#### createProductPanel(): Create product panel
#### showSelectionDialog(): Create a dialog with checkboxes and quantity selectors for the available cupcakes, count totalnumber of selected cupcakes, add selected quantity of cupcakes to the selectedCupcakes list, Validate the number of selected cupcakes based on the product type    

##### Convert to a list of strings with quantities for easier formatting
    List<String> cupcakeEntries = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : cupcakeCount.entrySet()) {
        cupcakeEntries.add(entry.getValue() + " " + entry.getKey());
    }

##### Build an HTML string with line breaks after every few cupcakes
    StringBuilder selectedCupcakesText = new StringBuilder("<html>Selected:<br>");
    int cupcakesPerLine = 3;
    for (int i = 0; i < cupcakeEntries.size(); i++) {
        selectedCupcakesText.append(cupcakeEntries.get(i));
        if ((i + 1) % cupcakesPerLine != 0 && i < cupcakeEntries.size() - 1) {
            selectedCupcakesText.append(", ");
        }
        if ((i + 1) % cupcakesPerLine == 0 && i < cupcakeEntries.size() - 1) {
            selectedCupcakesText.append("<br>");
        }
    }
    selectedCupcakesText.append("</html>");

##### Create a new Product instance for the bundle
    Product selectedBundleProduct = new Product(
        product.getName(), 
        product.getPrice(), 
        product.getImagePath(), 
        product.getCategory()
    );

##### Set the description to the HTML formatted string
    selectedBundleProduct.setSelectableCupcakes(new ArrayList<>(cupcakeCount.keySet()));
    selectedBundleProduct.setDescription(selectedCupcakesText.toString());

##### Add the bundle (1 item) to the cart
    cart.addItem(selectedBundleProduct, 1);

##### Update the total price in the cart
    totalLabel.setText("Total: $" + String.format("%.2f", cart.getTotalPrice()));

##### Open the cart window to display the selected bundle
    openCartWindow();
}

    
    
    
    
    
## Create Button with Icon

    private JButton createButtonWithIcon(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 30));
        try (InputStream is = getClass().getResourceAsStream(iconPath)) {
            if (is != null) {
                Image iconImage = ImageIO.read(is).getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(iconImage));
            }
        } catch (IOException e) {
            System.err.println("Could not load icon: " + iconPath);
        }
        return button;
    }

## Add Product to Cart

    private void addProductToCart(Product product) {
        cart.addItem(product, 1);
        totalLabel.setText("Total: $" + String.format("%.2f", cart.getTotalPrice()));
    }

    private void openCartWindow() {
        new CartWindow(cart);
    }

    private void displayProducts(List<Product> productsToDisplay) {
        productPanel.removeAll();
        for (Product product : productsToDisplay) {
            productPanel.add(createProductPanel(product));
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

## Create Sample Products
    private List<Product> createSampleProducts() {
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product("Vanilla Dream", 4.99, "images/vanilladream.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Fudge Delight", 4.99, "images/fudgedelight.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Strawberry Cake", 4.99, "images/strawberrycake.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Red Velvet", 4.99, "images/redvelvet.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Lemon Zest", 5.99, "images/lemonzest.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Blueberry Boom", 7.99, "images/blueberry.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Churro", 5.99, "images/churro.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("PB & Jelly", 6.99, "images/pbandj.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Salted Caramel", 6.99, "images/saltedcaramel.jpg", "Signature Flavors"));
        sampleProducts.add(new Product("Cranberry Cheer", 6.99, "images/cranberrycheer.jpg", "Seasonal"));
        sampleProducts.add(new Product("Holly Jolly Cupcake", 6.99, "images/hollyjollycupcake.jpg", "Seasonal"));
        sampleProducts.add(new Product("New Year's Sparkle", 7.99, "images/newyears.jpg", "Seasonal"));
        sampleProducts.add(new Product("Pupcake", 4.99, "images/pupcake.jpg", "Specials"));
        sampleProducts.add(new Product("Catcake", 4.99, "images/catcake.jpg", "Specials"));


        
### Add bundle products and define their selectable cupcakes
        Product jumboCupcake = new Product("Jumbo Cupcake", 19.99, "images/pickone.png", "Specials");
        jumboCupcake.setSelectableCupcakes(List.of(
        "Vanilla Dream", "Fudge Delight", "Strawberry Cake", "Red Velvet", 
        "Lemon Zest", "Blueberry Boom", "Churro", "PB & Jelly", 
        "Salted Caramel", "Cranberry Cheer", "Holly Jolly Cupcake", "New Year's Sparkle"));
        sampleProducts.add(jumboCupcake);

        Product miniTrio = new Product("Mini Trio", 8.99, "images/pickthree.png", "Specials");
        miniTrio.setSelectableCupcakes(List.of(
        "Vanilla Dream", "Fudge Delight", "Strawberry Cake", "Red Velvet", 
        "Lemon Zest", "Blueberry Boom", "Churro", "PB & Jelly", 
        "Salted Caramel", "Cranberry Cheer", "Holly Jolly Cupcake", "New Year's Sparkle"));
        sampleProducts.add(miniTrio);

        Product halfDozen = new Product("Half Dozen", 13.99, "images/picksix.png", "Specials");
        halfDozen.setSelectableCupcakes(List.of(
        "Vanilla Dream", "Fudge Delight", "Strawberry Cake", "Red Velvet", 
        "Lemon Zest", "Blueberry Boom", "Churro", "PB & Jelly", 
        "Salted Caramel", "Cranberry Cheer", "Holly Jolly Cupcake", "New Year's Sparkle"));
        sampleProducts.add(halfDozen);

        Product dozen = new Product("Dozen", 19.99, "images/picktwelve.png", "Specials");
        dozen.setSelectableCupcakes(List.of(
        "Vanilla Dream", "Fudge Delight", "Strawberry Cake", "Red Velvet", 
        "Lemon Zest", "Blueberry Boom", "Churro", "PB & Jelly", 
        "Salted Caramel", "Cranberry Cheer", "Holly Jolly Cupcake", "New Year's Sparkle"));
        sampleProducts.add(dozen);

        return sampleProducts;
    }

