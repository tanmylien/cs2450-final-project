package gui;

import model.Cart;

import javax.swing.*;
import java.awt.*;

public class CheckoutWindow extends JFrame {
    private Cart cart;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JLabel totalLabel;

    public CheckoutWindow(Cart cart) {
        this.cart = cart;

        setTitle("Checkout");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Hiển thị tổng tiền
        totalLabel = new JLabel("Total: $" + cart.getTotalPrice(), SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalLabel, BorderLayout.NORTH);

        // Panel cho thông tin khách hàng
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        infoPanel.add(nameField);

        infoPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        infoPanel.add(addressField);

        infoPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        infoPanel.add(phoneField);

        add(infoPanel, BorderLayout.CENTER);

        // Nút đặt hàng
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> placeOrder());
        add(placeOrderButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void placeOrder() {
        // Kiểm tra điều kiện đầu vào
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Xử lý đặt hàng thành công
        JOptionPane.showMessageDialog(this, "Order placed successfully!", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
        cart.clear(); // Xóa dữ liệu trong giỏ hàng
        dispose(); // Đóng cửa sổ CheckoutWindow
    }
}
