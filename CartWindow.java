package gui;

import model.Cart;
import model.CartItem;

import javax.swing.*;
import java.awt.*;

public class CartWindow extends JFrame {
    private JPanel itemsPanel;
    private Cart cart;
    private JLabel totalPriceLabel;

    public CartWindow(Cart cart) {
        this.cart = cart;

        setTitle("Your Cart");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.blue);

        loadCartItems();

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Khu vực hiển thị tổng tiền và nút Checkout
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(173, 216, 230));

        // Hiển thị tổng tiền ở bên trái
        totalPriceLabel = new JLabel("Total: $" + String.format("%.2f",calculateTotalPrice()));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);

        // Nút Checkout ở bên phải
        JButton checkoutButton = new JButton("CHECKOUT");
        checkoutButton.setPreferredSize(new Dimension(100, 30));
        checkoutButton.setFont(new Font("Times New Roman", Font.BOLD,14));
        checkoutButton.setBackground(Color.pink);
        checkoutButton.setForeground(Color.black);
        checkoutButton.setFocusPainted(false);
        checkoutButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkoutButton.addActionListener(e -> proceedToCheckout());
        bottomPanel.add(checkoutButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Hàm xử lý khi nhấn nút Checkout
    private void proceedToCheckout() {
        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Add items to proceed.", "Empty Cart",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            dispose(); // Đóng cửa sổ CartWindow
            new CheckoutWindow(cart); // Mở cửa sổ CheckoutWindow
        }
    }

    // Tính tổng tiền đơn hàng
    private double calculateTotalPrice() {
        double total = 0.00;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Tải và hiển thị các mục trong giỏ hàng
    private void loadCartItems() {
        itemsPanel.removeAll();

        for (CartItem item : cart.getItems()) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            itemPanel.setPreferredSize(new Dimension(450, 140));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/" + item.getProduct().getImagePath()));
            JLabel imageLabel = new JLabel();
            Image image = imageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
            itemPanel.add(imageLabel);

            JLabel nameLabel = new JLabel(item.getProduct().getName());
            nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
            nameLabel.setBorder(BorderFactory.createEtchedBorder());
            itemPanel.add(nameLabel);

            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            JButton decreaseButton = new JButton("-");
            decreaseButton.setPreferredSize(new Dimension(40,30));
            decreaseButton.setFont(new Font("Times New Roman", Font.BOLD,14));
            decreaseButton.setBackground(Color.pink);
            decreaseButton.setForeground(Color.black);
            decreaseButton.setFocusPainted(false);
            decreaseButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            decreaseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


            JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
            quantityLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
            JButton increaseButton = new JButton("+");
            increaseButton.setPreferredSize(new Dimension(40,30));
            increaseButton.setFont(new Font("Times New Roman", Font.BOLD,14));
            increaseButton.setBackground(Color.pink);
            increaseButton.setForeground(Color.black);
            increaseButton.setFocusPainted(false);
            increaseButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            increaseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            decreaseButton.addActionListener(e -> {
                item.decreaseQuantity();
                if (item.getQuantity() == 0) {
                    cart.removeItem(item.getProduct());
                }
                refreshCartItems();
            });

            increaseButton.addActionListener(e -> {
                item.increaseQuantity();
                refreshCartItems();
            });

            quantityPanel.add(decreaseButton);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(increaseButton);
            itemPanel.add(quantityPanel);

            JLabel priceLabel = new JLabel("Price: $" + String.format("%.2f",item.getTotalPrice()));
            JButton removeButton = new JButton("Remove");
            removeButton.setPreferredSize(new Dimension(60,30));
            removeButton.setFont(new Font("Times New Roman", Font.PLAIN,14));
            removeButton.setBackground(Color.pink);
            removeButton.setForeground(Color.black);
            removeButton.setFocusPainted(false);
            removeButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            removeButton.addActionListener(e -> {
                cart.removeItem(item.getProduct());
                refreshCartItems();
            });

            itemPanel.add(priceLabel);
            itemPanel.add(removeButton);

            itemsPanel.add(itemPanel);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void refreshCartItems() {
        loadCartItems();
        totalPriceLabel.setText("Total: $" + String.format("%.2f",calculateTotalPrice()));
    }
}
