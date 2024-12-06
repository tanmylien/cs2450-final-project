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
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        loadCartItems();

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Khu vực hiển thị tổng tiền và nút Checkout
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Hiển thị tổng tiền ở bên trái
        totalPriceLabel = new JLabel("Total: $" + calculateTotalPrice());
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);

        // Nút Checkout ở bên phải
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(100, 30));
        checkoutButton.addActionListener(e -> proceedToCheckout());
        bottomPanel.add(checkoutButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Hàm xử lý khi nhấn nút Checkout
    private void proceedToCheckout() {
        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Add items to proceed.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
        } else {
            dispose(); // Đóng cửa sổ CartWindow
            new CheckoutWindow(cart); // Mở cửa sổ CheckoutWindow
        }
    }

    // Tính tổng tiền đơn hàng
    private double calculateTotalPrice() {
        double total = 0.0;
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
            itemPanel.setPreferredSize(new Dimension(450, 100));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/" + item.getProduct().getImagePath()));
            JLabel imageLabel = new JLabel();
            Image image = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
            itemPanel.add(imageLabel);

            JLabel nameLabel = new JLabel(item.getProduct().getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            itemPanel.add(nameLabel);

            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            JButton decreaseButton = new JButton("-");
            JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
            JButton increaseButton = new JButton("+");

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

            JLabel priceLabel = new JLabel("Price: $" + item.getTotalPrice());
            JButton removeButton = new JButton("Remove");

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
        totalPriceLabel.setText("Total: $" + calculateTotalPrice());
    }
}
