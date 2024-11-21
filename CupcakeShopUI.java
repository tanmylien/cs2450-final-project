package gui;

import model.Cart;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CupcakeShopUI extends JFrame {
    private List<Product> products;
    private JPanel productPanel;
    private JTextField searchField;
    private JSlider priceSlider;
    private List<JCheckBox> occasionCheckboxes = new ArrayList<>();
    private ButtonGroup sizeGroup = new ButtonGroup();
    private Cart cart;
    private JLabel totalLabel;

    public CupcakeShopUI() {
        cart = new Cart();

        setTitle("Online Cupcake Store");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(135, 206, 250)); // Mustard yellow background

        // Sample product list
        products = createSampleProducts();

        // Tab Products
        JPanel productGridPanel = new JPanel(new BorderLayout());
        productGridPanel.setBackground(new Color(135, 206, 250));

        // Search bar and cart button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 216, 230));

        // Rounded search field
        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setBackground(Color.WHITE);
        searchField.setOpaque(true);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        searchField.setBorder(BorderFactory.createCompoundBorder(searchField.getBorder(), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        JButton searchButton = createButtonWithIcon("Search", "/icons/search.png");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> filterProducts());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(173, 216, 230));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        totalLabel = new JLabel("Total: $0");
        JButton cartButton = createButtonWithIcon("Cart", "/icons/cart.png");
        cartButton.setPreferredSize(new Dimension(100, 30));
        cartButton.addActionListener(e -> openCartWindow());

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(new Color(173, 216, 230));
        cartPanel.add(totalLabel, BorderLayout.CENTER);
        cartPanel.add(cartButton, BorderLayout.EAST);

        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(cartPanel, BorderLayout.EAST);

        // Filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(Color.WHITE); // Set background to white
        filterPanel.setPreferredSize(new Dimension(200, getHeight()));

        filterPanel.add(new JLabel("SPECIAL OCCASIONS"));
        String[] occasions = {"Halloween", "Birthday", "Christmas", "Valentine", "Easter", "New Year's", "Thanksgiving", "Fourth of July"};
        for (String occasion : occasions) {
            JCheckBox checkBox = new JCheckBox(occasion);
            checkBox.setBackground(Color.WHITE);
            occasionCheckboxes.add(checkBox);
            checkBox.addActionListener(e -> filterProducts());
            filterPanel.add(checkBox);
        }

        filterPanel.add(new JLabel("CUPCAKE SIZE"));
        String[] sizes = {"Mini", "Standard", "Jumbo", "Individual cupcake", "Pack of 4-6", "Dozen", "Two Dozen", "Mini cupcake pack"};
        for (String size : sizes) {
            JRadioButton sizeButton = new JRadioButton(size);
            sizeButton.setBackground(Color.WHITE);
            sizeGroup.add(sizeButton);
            sizeButton.addActionListener(e -> filterProducts());
            filterPanel.add(sizeButton);
        }

        filterPanel.add(new JLabel("PRICE RANGE"));
        priceSlider = new JSlider(0, 100);
        priceSlider.setMajorTickSpacing(20);
        priceSlider.setPaintTicks(true);
        priceSlider.setPaintLabels(true);
        priceSlider.setBackground(Color.WHITE);
        priceSlider.addChangeListener(e -> filterProducts());
        filterPanel.add(priceSlider);

        filterPanel.add(Box.createVerticalStrut(10));
        
        // Clear filter button with icon
        JButton clearFilterButton = createButtonWithIcon("Clear Filter", "/icons/clear.png");
        clearFilterButton.setPreferredSize(new Dimension(120, 30));
        clearFilterButton.addActionListener(e -> resetFilters());
        filterPanel.add(clearFilterButton);
        
        // Product display area
        productPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        productPanel.setBackground(new Color(173, 216, 230));
        displayProducts(products);

        JPanel productContainer = new JPanel(new BorderLayout());
        productContainer.setBackground(new Color(173, 216, 230));
        productContainer.add(productPanel, BorderLayout.CENTER);
        productContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(productContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        productGridPanel.add(topPanel, BorderLayout.NORTH);
        productGridPanel.add(filterPanel, BorderLayout.WEST);
        productGridPanel.add(scrollPane, BorderLayout.CENTER);

        add(productGridPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private List<Product> createSampleProducts() {
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product("Chocolate Cupcake", 10.00, "images/cupcake0.jpg", "Halloween", "Standard"));
        sampleProducts.add(new Product("Cranberry Cheer", 15.00, "images/cupcake2.jpg", "Christmas", "Jumbo"));
        sampleProducts.add(new Product("Holly Jolly Cupcake", 12.00, "images/cupcake1.jpg", "Christmas", "Mini"));
        sampleProducts.add(new Product("Jingle Bell Bakes", 8.00, "images/cupcake3.jpg", "Christmas", "Standard"));
        sampleProducts.add(new Product("Shimmering Sprinkles", 20.00, "images/cupcake4.jpg", "Christmas", "Jumbo"));
        sampleProducts.add(new Product("Snowflake Swirls", 9.00, "images/cupcake7.jpg", "Christmas", "Mini"));
        sampleProducts.add(new Product("Cupcake G", 14.00, "images/cupcake0.jpg", "Christmas", "Standard"));
        sampleProducts.add(new Product("Cupcake H", 17.00, "images/cupcake0.jpg", "Valentine", "Jumbo"));
        sampleProducts.add(new Product("Cupcake I", 11.00, "images/cupcake0.jpg", "Halloween", "Mini"));
        sampleProducts.add(new Product("Cupcake J", 18.00, "images/cupcake0.jpg", "Birthday", "Standard"));
        sampleProducts.add(new Product("Cupcake I", 11.00, "images/cupcake0.jpg", "Halloween", "Mini"));
        sampleProducts.add(new Product("Cupcake J", 18.00, "images/cupcake0.jpg", "Birthday", "Standard"));
        sampleProducts.add(new Product("Cupcake I", 11.00, "images/cupcake0.jpg", "Halloween", "Mini"));
        sampleProducts.add(new Product("Cupcake J", 18.00, "images/cupcake0.jpg", "Birthday", "Standard"));
        return sampleProducts;
    }

    private void displayProducts(List<Product> products) {
        productPanel.removeAll();
        productPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.NONE; // Do not resize components to fill cell
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around items
        gbc.anchor = GridBagConstraints.NORTH; // Align components to the top of the cell

        int column = 0;
        int row = 0;

        for (Product product : products) {
            gbc.gridx = column;
            gbc.gridy = row;
            
            JPanel productItem = createProductPanel(product);
            productPanel.add(productItem, gbc);

            column++;
            if (column == 4) { // Move to next row after 4 items
                column = 0;
                row++;
            }
        }
        
        productPanel.revalidate();
        productPanel.repaint();
    }

    private void filterProducts() {
        String searchQuery = searchField.getText().toLowerCase();
        int maxPrice = priceSlider.getValue();
        String selectedSize = getSelectedSize();
        List<String> selectedOccasions = getSelectedOccasions();

        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            boolean matchesSearch = product.getName().toLowerCase().contains(searchQuery);
            boolean matchesPrice = product.getPrice() <= maxPrice;
            boolean matchesSize = selectedSize.isEmpty() || product.getSize().equals(selectedSize);
            boolean matchesOccasion = selectedOccasions.isEmpty() || selectedOccasions.contains(product.getOccasion());

            if (matchesSearch && matchesPrice && matchesSize && matchesOccasion) {
                filteredProducts.add(product);
            }
        }
        displayProducts(filteredProducts);
    }

    private void resetFilters() {
        searchField.setText("");
        priceSlider.setValue(100);
        sizeGroup.clearSelection();
        occasionCheckboxes.forEach(cb -> cb.setSelected(false));
        displayProducts(products);
    }

    private String getSelectedSize() {
        for (Enumeration<AbstractButton> buttons = sizeGroup.getElements(); buttons.hasMoreElements();) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }

    private List<String> getSelectedOccasions() {
        List<String> selectedOccasions = new ArrayList<>();
        for (JCheckBox checkBox : occasionCheckboxes) {
            if (checkBox.isSelected()) {
                selectedOccasions.add(checkBox.getText());
            }
        }
        return selectedOccasions;
    }

    private JPanel createProductPanel(Product product) {
        JPanel productItem = new JPanel();
        productItem.setLayout(new BoxLayout(productItem, BoxLayout.Y_AXIS));
        productItem.setPreferredSize(new Dimension(150, 190)); // Fixed size for product items
        productItem.setMaximumSize(new Dimension(150, 190));   // Enforce fixed height
        productItem.setBackground(Color.WHITE);
        productItem.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(10, 10, 10, 10))); // Border and padding

        // Product image
        JLabel productImage;
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/" + product.getImagePath()));
        if (imageIcon.getIconWidth() == -1) {
            productImage = new JLabel();
            productImage.setPreferredSize(new Dimension(100, 100));
            productImage.setOpaque(true);
            productImage.setBackground(Color.LIGHT_GRAY);
        } else {
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            productImage = new JLabel(new ImageIcon(image));
        }
        productImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        productItem.add(productImage);

        // Product name and price labels
        JLabel productLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        JLabel priceLabel = new JLabel("$" + product.getPrice(), SwingConstants.CENTER);
        productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        productItem.add(productLabel);
        productItem.add(priceLabel);

        // Add to cart button
        JButton addToCartButton = createButtonWithIcon("Add to cart", "/icons/cart.png");
        addToCartButton.setPreferredSize(new Dimension(120, 30));
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> addProductToCart(product));

        productItem.add(addToCartButton);

        return productItem;
    }


    private void addProductToCart(Product product) {
        cart.addItem(product, 1);
        System.out.println(product.getName() + " added to cart. Current quantity: " + cart.getItems().size());
        totalLabel.setText("Total: $" + cart.getTotalPrice());
    }

    private void openCartWindow() {
        new CartWindow(cart);
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
            System.err.println("Icon not found for " + text);
        }
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CupcakeShopUI::new);
    }
}
