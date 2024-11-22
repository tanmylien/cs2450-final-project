package final_project;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void increaseQuantity(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity();
                return;
            }
        }
    }

    public void decreaseQuantity(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.decreaseQuantity();
                return;
            }
        }
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
