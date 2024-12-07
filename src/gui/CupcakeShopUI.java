package gui;

import model.Cart;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CupcakeShopUI extends JFrame {
    private List<Product> products;
    private JPanel productPanel;
    private JTextField searchField;
    private Cart cart;
    private JLabel totalLabel;

    public CupcakeShopUI() {
        cart = new Cart();
        products = createSampleProducts();

        setTitle("Online Cupcake Store");
        setSize(1400, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#D5ADEB"));
        setLayout(new BorderLayout());

        JPanel productGridPanel = new JPanel(new BorderLayout());
        productGridPanel.setBackground(Color.decode("#D5ADEB"));

        // Top Panel (Logo, Search, Cart)
        JPanel topPanel = createTopPanel();
        productGridPanel.add(topPanel, BorderLayout.NORTH);

        // Filter Panel
        addFilterPanel(productGridPanel);

        // Product Display Area
        productPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        productPanel.setBackground(Color.decode("#D8A6DB"));
        displayProducts(products);

        JPanel productContainer = new JPanel(new BorderLayout());
        productContainer.setBackground(Color.decode("#D8A6DB"));
        productContainer.add(productPanel, BorderLayout.CENTER);
        productContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(productContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        productGridPanel.add(scrollPane, BorderLayout.CENTER);

        add(productGridPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.decode("#D5ADEB"));

        // Logo
        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/images/sugarpawsbakery (1).jpg")));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createVerticalStrut(20));
        topPanel.add(logo);
        topPanel.add(Box.createVerticalStrut(20));

        // Search and Cart panels combined horizontally
        JPanel horizontalPanel = new JPanel(new BorderLayout());
        horizontalPanel.setBackground(Color.decode("#D5ADEB"));

        // Search Panel
        JPanel searchPanel = createSearchPanel();

        // Cart Panel
        JPanel cartPanel = createCartPanel();

        horizontalPanel.add(searchPanel, BorderLayout.WEST);
        horizontalPanel.add(cartPanel, BorderLayout.EAST);

        topPanel.add(horizontalPanel);

        return topPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.decode("#D5ADEB"));

        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setBackground(Color.decode("#EDD7ED"));
        searchField.setOpaque(true);

        JButton searchButton = createButtonWithIcon("Search", "/icons/search.png");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setBackground(Color.decode("#EDD7ED"));
        searchButton.addActionListener(e -> performSearch());

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(Color.decode("#D5ADEB"));

        totalLabel = new JLabel("Total: $0.00");
        JButton cartButton = createButtonWithIcon("Cart", "/icons/cart.png");
        cartButton.setPreferredSize(new Dimension(100, 30));
        cartButton.setBackground(Color.decode("#D5ADEB"));
        cartButton.addActionListener(e -> openCartWindow());

        cartPanel.add(totalLabel, BorderLayout.CENTER);
        cartPanel.add(cartButton, BorderLayout.EAST);

        return cartPanel;
    }

    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            displayProducts(products);
        } else {
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getName().toLowerCase().contains(searchText)) {
                    filteredProducts.add(product);
                }
            }
            displayProducts(filteredProducts);
        }
    }

    private void addFilterPanel(JPanel parentPanel) {
        JPanel filterPanel = createFilterPanel();
        parentPanel.add(filterPanel, BorderLayout.WEST);
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(Color.decode("#EDD7ED"));
        filterPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Category filter
        filterPanel.add(new JLabel("Sort By Category"));
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
        filterPanel.add(Box.createVerticalStrut(10));

        // Price filter
        filterPanel.add(new JLabel("Sort By Price"));
        JSlider priceSlider = createPriceSlider();
        filterPanel.add(priceSlider);

        // Clear filter button
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

    // Filtering Methods
    private void filterCategory(String category) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        displayProducts(filteredProducts);
    }

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

    private void resetFilters() {
        displayProducts(products);
    }

    private Hashtable<Integer, JLabel> createCustomPriceLabels() {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("<$5"));
        labelTable.put(5, new JLabel("$10"));
        labelTable.put(10, new JLabel("$15"));
        labelTable.put(15, new JLabel("$20"));
        return labelTable;
    }

    // Product & Cart Methods
    private JPanel createProductPanel(Product product) {
        JPanel productItem = new JPanel();
        productItem.setLayout(new BoxLayout(productItem, BoxLayout.Y_AXIS));
        productItem.setPreferredSize(new Dimension(250, 350));
        productItem.setBackground(Color.decode("#EDD7ED"));
        productItem.setBorder(new CompoundBorder(new LineBorder(Color.decode("#D5BDD5"), 1),
                new EmptyBorder(10, 10, 10, 10)));

        JLabel productImage = new JLabel(new ImageIcon(getClass().getResource("/" + product.getImagePath())));
        productImage.setPreferredSize(new Dimension(250, 250));
        productImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel productLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()), SwingConstants.CENTER);

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.setPreferredSize(new Dimension(120, 30));
        addToCartButton.addActionListener(e -> addProductToCart(product));

        productItem.add(productImage);
        productItem.add(productLabel);
        productItem.add(priceLabel);
        productItem.add(addToCartButton);

        return productItem;
    }

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

    // Sample Data
    private List<Product> createSampleProducts() {
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product("Vanilla Dream", 4.99, "images/vanilladream.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Fudge Delight", 4.99, "images/fudgedelight.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Strawberry Cake", 4.99, "images/strawberrycake.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Red Velvet", 4.99, "images/redvelvet.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Lemon Zest", 5.99, "images/lemonzest.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Churro", 5.99, "images/churro.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("PB & Jelly", 6.99, "images/pb&j.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Salted Caramel", 6.99, "images/saltedcaramel.jpeg", "Signature Flavors"));
        sampleProducts.add(new Product("Mini Trio", 9.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Half Dozen", 14.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Dozen", 19.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Jumbo Cupcake", 19.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Mini Pupcake", 2.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Pupcake", 4.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Mini Catcake", 2.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Catcake", 4.99, "images/vanilladream.jpeg", "Specials"));
        sampleProducts.add(new Product("Cranberry Cheer", 6.99, "images/cranberrycheer.jpg", "Seasonal"));
        sampleProducts.add(new Product("Holly Jolly Cupcake", 6.99, "images/hollyjollycupcake.jpg", "Seasonal"));
        sampleProducts.add(new Product("Merry Blueberry", 7.99, "images/merryblueberry.jpeg", "Seasonal"));
        sampleProducts.add(new Product("New Year's Sparkle", 7.99, "images/newyearsparkle.jpeg", "Seasonal"));
        return sampleProducts;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CupcakeShopUI::new);
    }
}
