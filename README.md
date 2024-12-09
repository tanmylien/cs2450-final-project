# Cupcake Shop GUI Group #10

## 👤 Group members: Aaron Aramburo, Natasha Budiman, Jennifer Pedomoro, Jennifer Garcia, My Lien Tan
## Purpose
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

# Screenshots
    ![Screenshot 2024-12-08 222327](https://github.com/user-attachments/assets/321cf018-cc3f-4fcc-9ff0-682d6e78ca6e)
    ![Screenshot 2024-12-08 222327](https://github.com/user-attachments/assets/4dcbf4c3-6026-4469-be83-af6763e3cbdf)





# Code Functions
## createTopPanel() : Create top panel (set backgorunds, labels, and create Cart Panel)
## createCartPanel() : Create Cart Panel (set backgrounds, labels, buttons)
## createSearchPanel() : Create Search Panel (set backgrounds, search field, buttons)
## performSearch() : Function to perform search feature 

## Add Filter Panel

    private void addFilterPanel(JPanel parentPanel) {
        JPanel filterPanel = createFilterPanel();
        JScrollPane filterScrollPane = new JScrollPane(filterPanel);
        filterScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        filterScrollPane.setPreferredSize(new Dimension(325, getHeight()));
        parentPanel.add(filterScrollPane, BorderLayout.WEST);
    }
## Create Filter Panel
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new GridLayout(9, 1, 0, 5));
        filterPanel.setBackground(Color.decode("#EDD7ED"));

        JPanel searchPanel = createSearchPanel();
        filterPanel.add(searchPanel);

        JLabel categoryLabel = new JLabel("Sort By Category");
        categoryLabel.setFont(categoryLabel.getFont().deriveFont(Font.BOLD));
        filterPanel.add(categoryLabel);

        ButtonGroup categoryGroup = new ButtonGroup();
        JRadioButton allCategories = new JRadioButton("All Cupcakes", true);
        JRadioButton signatureFlavors = new JRadioButton("Signature Flavors");
        JRadioButton seasonal = new JRadioButton("Seasonal");
        JRadioButton specials = new JRadioButton("Specials");

        setRadioButtonBackground(allCategories, signatureFlavors, seasonal, specials);

        categoryGroup.add(allCategories);
        categoryGroup.add(signatureFlavors);
        categoryGroup.add(seasonal);
        categoryGroup.add(specials);

        allCategories.addActionListener(e -> displayProducts(products));
        signatureFlavors.addActionListener(e -> filterCategory("Signature Flavors"));
        seasonal.addActionListener(e -> filterCategory("Seasonal"));
        specials.addActionListener(e -> filterCategory("Specials"));

        filterPanel.add(allCategories);
        filterPanel.add(signatureFlavors);
        filterPanel.add(seasonal);
        filterPanel.add(specials);

        JLabel priceLabel = new JLabel("Sort By Price");
        priceLabel.setFont(priceLabel.getFont().deriveFont(Font.BOLD));
        filterPanel.add(priceLabel);

        JSlider priceSlider = createPriceSlider();
        filterPanel.add(priceSlider);

        JButton clearFilterButton = new JButton("Clear Filters");
        clearFilterButton.setPreferredSize(new Dimension(120, 30));
        clearFilterButton.addActionListener(e -> resetFilters());
        filterPanel.add(clearFilterButton);

        return filterPanel;
    }

    private void setRadioButtonBackground(JRadioButton... buttons) {
        for (JRadioButton button : buttons) {
            button.setBackground(Color.decode("#EDD7ED"));
        }
    }
## Create Price Slider

    private JSlider createPriceSlider() {
        JSlider priceSlider = new JSlider(0, 15, 15);
        priceSlider.setMajorTickSpacing(5);
        priceSlider.setPaintTicks(true);
        priceSlider.setPaintLabels(true);
        priceSlider.setSnapToTicks(true);
        priceSlider.setBackground(Color.decode("#EDD7ED"));
        priceSlider.setLabelTable(createCustomPriceLabels());

        priceSlider.addChangeListener(e -> filterProductsBySlider(priceSlider.getValue()));
        return priceSlider;
    }


## Create Filter Category
    private void filterCategory(String category) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        displayProducts(filteredProducts);
    }

## Filter Products By Slider Value

    private void filterProductsBySlider(int value) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            double price = product.getPrice();
            if ((value == 0 && price < 5) ||
                (value == 5 && price >= 5 && price < 10) ||
                (value == 10 && price >= 10 && price < 15) ||
                (value == 15 && price >= 15 && price < 20)) {
                filteredProducts.add(product);
            }
        }
        displayProducts(filteredProducts);
    }
## Reset Filter Function

    private void resetFilters() {
        displayProducts(products);
    }

## Create Custom Price Labels

    private Hashtable<Integer, JLabel> createCustomPriceLabels() {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("<$5"));
        labelTable.put(5, new JLabel("$10"));
        labelTable.put(10, new JLabel("$15"));
        labelTable.put(15, new JLabel("$20"));
        return labelTable;
    }


## Create Product Panel
    private JPanel createProductPanel(Product product) {
        JPanel productItem = new JPanel();
        productItem.setLayout(new BoxLayout(productItem, BoxLayout.Y_AXIS)); // Keep BoxLayout
        productItem.setPreferredSize(new Dimension(250, 350));
        productItem.setBackground(Color.decode("#EDD7ED"));
        productItem.setBorder(new CompoundBorder(new LineBorder(Color.decode("#D5BDD5"), 1),
                new EmptyBorder(10, 10, 10, 10)));
    
##### Product Image Centered
        JLabel productImage = new JLabel(new ImageIcon(getClass().getResource("/" + product.getImagePath())));
        productImage.setPreferredSize(new Dimension(250, 250));
        productImage.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centering image
    
##### Product name in bold
        JLabel productLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        productLabel.setFont(productLabel.getFont().deriveFont(Font.BOLD));  // Make product name bold
        productLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centering product name
    
##### Price label centered
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()), SwingConstants.CENTER);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centering price
    
##### Add to cart button centered
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setPreferredSize(new Dimension(120, 30));
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> {
            if (!product.getSelectableCupcakes().isEmpty()) {
                // Show selection dialog for bundles
                showSelectionDialog(product);
            } else {
                // Directly add non-bundle product to the cart
                addProductToCart(product);
            }
        });
    
        productItem.add(productImage);
        productItem.add(productLabel);
        productItem.add(priceLabel);
        productItem.add(addToCartButton);
    
        return productItem;
    }

## Show Selection Dialog

    private void showSelectionDialog(Product product) {
##### Create a dialog with checkboxes and quantity selectors for the available cupcakes
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBackground(Color.decode("#D8A6DB"));
        
        List<JSpinner> spinners = new ArrayList<>();
        List<String> cupcakeNames = product.getSelectableCupcakes();
        
##### Create a spinner for each selectable cupcake
        for (String cupcake : cupcakeNames) {
            JPanel cupcakePanel = new JPanel(new BorderLayout());
            cupcakePanel.setBackground(Color.decode("#D8A6DB"));
        
            JLabel cupcakeLabel = new JLabel(cupcake);
            cupcakeLabel.setBackground(Color.decode("#D8A6DB"));
            cupcakePanel.add(cupcakeLabel, BorderLayout.WEST);
        
##### Spinner to choose quantity
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));  // Allow quantities from 0 to 12
            spinners.add(spinner);
            cupcakePanel.add(spinner, BorderLayout.EAST);
        
            selectionPanel.add(cupcakePanel);
        }
        
##### Add an OK button to confirm the selection
        JButton okButton = new JButton("Confirm Selection");
        okButton.addActionListener(e -> {
            List<String> selectedCupcakes = new ArrayList<>();
            int totalSelected = 0;
        
##### Count the total number of selected cupcakes
            for (int i = 0; i < cupcakeNames.size(); i++) {
                int quantity = (int) spinners.get(i).getValue();  // Get the quantity from the spinner
                totalSelected += quantity;
        
 ##### Add the selected quantity of cupcakes to the selectedCupcakes list
                for (int j = 0; j < quantity; j++) {
                    selectedCupcakes.add(cupcakeNames.get(i));
                }
            }
        
##### Validate the number of selected cupcakes based on the product type
            if (product.getName().equals("Mini Trio") && totalSelected == 3) {
                addSelectedCupcakesToCart(product, selectedCupcakes);
                selectionDialog.dispose(); // Close the dialog
            } else if (product.getName().equals("Half Dozen") && totalSelected == 6) {
                addSelectedCupcakesToCart(product, selectedCupcakes);
                selectionDialog.dispose(); // Close the dialog
            } else if (product.getName().equals("Dozen") && totalSelected == 12) {
                addSelectedCupcakesToCart(product, selectedCupcakes);
                selectionDialog.dispose(); // Close the dialog
            } else if (product.getName().equals("Jumbo Cupcake") && totalSelected == 1) {
                addSelectedCupcakesToCart(product, selectedCupcakes);
                selectionDialog.dispose(); // Close the dialog
            } else {
                String limitMessage = "You must select exactly ";
                if (product.getName().equals("Mini Trio")) {
                    limitMessage += "3 cupcakes for the Mini Trio.";
                } else if (product.getName().equals("Half Dozen")) {
                    limitMessage += "6 cupcakes for the Half Dozen.";
                } else if (product.getName().equals("Dozen")) {
                    limitMessage += "12 cupcakes for the Dozen.";
                } else if (product.getName().equals("Jumbo Cupcake")) {
                    limitMessage += "1 Jumbo Cupcake.";
                }
                JOptionPane.showMessageDialog(this,
                        limitMessage, "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        selectionPanel.add(okButton);
        
##### Create a dialog window for selecting cupcakes
        selectionDialog = new JDialog(this, "Select Cupcakes", true);
        selectionDialog.setLayout(new BorderLayout());
        selectionDialog.add(selectionPanel, BorderLayout.CENTER);
        selectionDialog.setSize(300, 400);
        selectionDialog.setLocationRelativeTo(this);
        selectionDialog.setVisible(true);
    }
    
    
    
## Add Selected Cupcakes To Cart
    
    private void addSelectedCupcakesToCart(Product product, List<String> selectedCupcakes) {
    // Track the quantity of each selected cupcake
    HashMap<String, Integer> cupcakeCount = new HashMap<>();

    for (String cupcake : selectedCupcakes) {
        cupcakeCount.put(cupcake, cupcakeCount.getOrDefault(cupcake, 0) + 1);
    }

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

