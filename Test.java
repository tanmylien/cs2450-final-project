package final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test extends JFrame {
    private double totalPrice = 0.0;

    public Test() {
        setTitle("Online Cupcake Store");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Product Grid with "Customize my cake" button and filters
        JPanel productGridPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JButton homeButton = new JButton("Home");
        JTextField searchField = new JTextField("Product Search");
        searchField.setPreferredSize(new Dimension(800, 30));
        searchField.setMinimumSize(new Dimension(800, 30));
        searchField.setMaximumSize(new Dimension(800, 30));

       
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchField);

        JLabel totalLabel = new JLabel("Total: $0");
        JButton cartButton = new JButton("Cart");

        
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(homeButton, BorderLayout.WEST);

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(totalLabel, BorderLayout.CENTER);
        cartPanel.add(cartButton, BorderLayout.EAST);

        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(cartPanel, BorderLayout.EAST);

        // Left Panel for filters
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setPreferredSize(new Dimension(200, getHeight()));

        filterPanel.add(new JLabel("SPECIAL OCASIONS"));
        String[] brands = {"Halloween", "Birthday", "Chirstmas", "Valentine", "Easter", "New Year's", "ThanksGiving", "Fourth of July"};
        for (String brand : brands) {
            filterPanel.add(new JCheckBox(brand));
        }

        filterPanel.add(new JLabel("CUPCAKE SIZE"));
        String[] sizes = {"Mini", "Standard", "Jumbo", "Individual cupcake", "Pack of 4-6", "Dozen", "Two Dozen", "Mini cupcake pack"};
        ButtonGroup sizeGroup = new ButtonGroup();
        for (String size : sizes) {
            JRadioButton sizeButton = new JRadioButton(size);
            sizeGroup.add(sizeButton);
            filterPanel.add(sizeButton);
        }

        filterPanel.add(new JLabel("PRICE RANGE"));
        JSlider priceSlider = new JSlider(0, 100);
        priceSlider.setMajorTickSpacing(20);
        priceSlider.setPaintTicks(true);
        priceSlider.setPaintLabels(true);
        filterPanel.add(priceSlider);

      
        JPanel productPanel = new JPanel(new GridLayout(6, 3, 5, 5));
       
        addProductToGrid(productPanel, "Cupcake A", "$12", "C:\\Users\\ttan3\\Downloads\\cupcakea.jpg");
        addProductToGrid(productPanel, "Cupcake B", "$11", "C:\\Users\\ttan3\\Downloads\\cupcakeb.jpg");
        addProductToGrid(productPanel, "Cupcake C", "$15", "C:\\Users\\ttan3\\Downloads\\cupcakec.jpg");
        addProductToGrid(productPanel, "Cupcake D", "$19", "C:\\Users\\ttan3\\Downloads\\cupcaked.jpg");
        addProductToGrid(productPanel, "Cupcake E", "$11", "C:\\Users\\ttan3\\Downloads\\cupcakee.jpg");
        addProductToGrid(productPanel, "Cupcake F", "$10", "C:\\Users\\ttan3\\Downloads\\cupcakef.jpg");
        addProductToGrid(productPanel, "Cupcake G", "$10", "C:\\Users\\ttan3\\Downloads\\cupcakeg.jpg");
        addProductToGrid(productPanel, "Cupcake H", "$10", "C:\\Users\\ttan3\\Downloads\\cupcakeh.jpg");
        addProductToGrid(productPanel, "Cupcake I", "$10", "C:\\Users\\ttan3\\Downloads\\cupcakei.jpg");
        addProductToGrid(productPanel, "Cupcake J", "$10", "C:\\Users\\ttan3\\Downloads\\cupcakej.jpg");
        addProductToGrid(productPanel, "Cupcake K", "$12", "C:\\Users\\ttan3\\Downloads\\cupcakek.jpg");
        addProductToGrid(productPanel, "Cupcake L", "$13", "C:\\Users\\ttan3\\Downloads\\cupcakel.jpg");
        addProductToGrid(productPanel, "Cupcake M", "$14", "C:\\Users\\ttan3\\Downloads\\cupcakem.jpg");
        addProductToGrid(productPanel, "Cupcake N", "$19", "C:\\Users\\ttan3\\Downloads\\cupcakea.png");
        addProductToGrid(productPanel, "Cupcake O", "$21", "C:\\Users\\ttan3\\Downloads\\cupcakea.png");
        addProductToGrid(productPanel, "Cupcake P", "$13", "C:\\Users\\ttan3\\Downloads\\cupcakea.png");
        addProductToGrid(productPanel, "Cupcake Q", "$12", "C:\\Users\\ttan3\\Downloads\\cupcakea.png");
        addProductToGrid(productPanel, "Cupcake R", "$14", "C:\\Users\\ttan3\\Downloads\\cupcakea.png");
        addProductToGrid(productPanel, "Cupcake S", "$15", "C:\\Users\\ttan3\\Downloads\\cupcakea.png");

      
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton customizeButton = new JButton("Customize my cake");
        customizeButton.addActionListener(e -> tabbedPane.setSelectedIndex(1));

        productGridPanel.add(topPanel, BorderLayout.NORTH);
        productGridPanel.add(filterPanel, BorderLayout.WEST);
        productGridPanel.add(scrollPane, BorderLayout.CENTER);  // Add the scroll pane instead of productPanel
        productGridPanel.add(customizeButton, BorderLayout.SOUTH);

        // Remaining tabs for flavor, cream, toppings, and checkout
        JPanel flavorPanel = new JPanel(new GridLayout(4, 1));
        flavorPanel.setBorder(BorderFactory.createTitledBorder("Choose Cake Flavor"));

        JRadioButton chocolate = new JRadioButton("Chocolate - $5.00");
        JRadioButton vanilla = new JRadioButton("Vanilla - $4.50");
        JRadioButton strawberry = new JRadioButton("Strawberry - $5.50");

        chocolate.addActionListener(e -> totalPrice = 5.0);
        vanilla.addActionListener(e -> totalPrice = 4.5);
        strawberry.addActionListener(e -> totalPrice = 5.5);

        ButtonGroup flavorGroup = new ButtonGroup();
        flavorGroup.add(chocolate);
        flavorGroup.add(vanilla);
        flavorGroup.add(strawberry);

        flavorPanel.add(chocolate);
        flavorPanel.add(vanilla);
        flavorPanel.add(strawberry);
        JButton nextToCreamButton = new JButton("Next: Choose Cream Color");
        nextToCreamButton.addActionListener(e -> tabbedPane.setSelectedIndex(2));
        flavorPanel.add(nextToCreamButton);

        JPanel creamPanel = new JPanel(new GridLayout(3, 1));
        creamPanel.setBorder(BorderFactory.createTitledBorder("Choose Cream Color"));

        JRadioButton whiteCream = new JRadioButton("Vanilla Cream");
        JRadioButton pinkCream = new JRadioButton("Strawberry Cream");
        JRadioButton blueCream = new JRadioButton("Blueberry Cream");
        JRadioButton greenCream = new JRadioButton("Matcha Cream");

        whiteCream.setSelected(true);

        ButtonGroup creamGroup = new ButtonGroup();
        creamGroup.add(whiteCream);
        creamGroup.add(pinkCream);
        creamGroup.add(blueCream);
        creamGroup.add(greenCream);

        creamPanel.add(whiteCream);
        creamPanel.add(pinkCream);
        creamPanel.add(blueCream);
        creamPanel.add(greenCream);

        JButton nextToToppingsButton = new JButton("Next: Choose Toppings");
        nextToToppingsButton.addActionListener(e -> tabbedPane.setSelectedIndex(3));
        creamPanel.add(nextToToppingsButton);

        JPanel toppingsPanel = new JPanel(new GridLayout(4, 1));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Choose Toppings"));

        JCheckBox sprinkles = new JCheckBox("Sprinkles - $1.00");
        JCheckBox nuts = new JCheckBox("Nuts - $1.50");
        JCheckBox chocolateChips = new JCheckBox("Chocolate Chips - $1.25");
        JCheckBox chocolateSyrup = new JCheckBox ("Chocolate Syrup - $1.50");

        sprinkles.addActionListener(e -> totalPrice += sprinkles.isSelected() ? 1.0 : -1.0);
        nuts.addActionListener(e -> totalPrice += nuts.isSelected() ? 1.5 : -1.5);
        chocolateChips.addActionListener(e -> totalPrice += chocolateChips.isSelected() ? 1.25 : -1.25);
        chocolateSyrup.addActionListener(e -> totalPrice += chocolateSyrup.isSelected() ? 1.50 : -1.50);

        toppingsPanel.add(sprinkles);
        toppingsPanel.add(nuts);
        toppingsPanel.add(chocolateChips);
        toppingsPanel.add(chocolateSyrup);

        JButton nextToCheckoutButton = new JButton("Next: Checkout");
        nextToCheckoutButton.setPreferredSize(new Dimension(30, 30)); // Width: 150, Height: 40
        nextToCheckoutButton.setMinimumSize(new Dimension(30, 30));
        nextToCheckoutButton.setMaximumSize(new Dimension(30, 30));

        nextToCheckoutButton.addActionListener(e -> tabbedPane.setSelectedIndex(4));
        toppingsPanel.add(nextToCheckoutButton);

        JPanel checkoutPanel = new JPanel(new GridLayout(3, 1));
        JLabel checkoutTotalLabel = new JLabel("Total Price: $" + totalPrice);
        JCheckBox deliveryOption = new JCheckBox("Deliver to my address for an additional $20");

        deliveryOption.addActionListener(e -> {
            if (deliveryOption.isSelected()) {
                totalPrice += 20.0;
            } else {
                totalPrice -= 20.0;
            }
            checkoutTotalLabel.setText("Total Price: $" + totalPrice);
        });

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Order placed! Total: $" + totalPrice));

        checkoutPanel.add(checkoutTotalLabel);
        checkoutPanel.add(deliveryOption);
        checkoutPanel.add(placeOrderButton);

        tabbedPane.addTab("Products", productGridPanel);
        tabbedPane.addTab("Choose Flavor", flavorPanel);
        tabbedPane.addTab("Choose Cream", creamPanel);
        tabbedPane.addTab("Choose Toppings", toppingsPanel);
        tabbedPane.addTab("Checkout", checkoutPanel);

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addProductToGrid(JPanel productPanel, String productName, String price, String imagePath) {
        JPanel productItem = new JPanel();
        productItem.setLayout(new BoxLayout(productItem, BoxLayout.Y_AXIS));

      
        JLabel productImage;
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            productImage = new JLabel(new ImageIcon(image));
        } else {
            System.out.println("Image not found: " + imagePath);
            BufferedImage placeholderImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            productImage = new JLabel(new ImageIcon(placeholderImage));
        }

        productImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel productLabel = new JLabel(productName, SwingConstants.CENTER);
        productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel(price, SwingConstants.CENTER);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener((ActionEvent e) -> System.out.println(productName + " added to cart."));

        productItem.add(productImage);
        productItem.add(productLabel);
        productItem.add(priceLabel);
        productItem.add(addToCartButton);

        productPanel.add(productItem);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CupcakeShopUI());
    }
}


