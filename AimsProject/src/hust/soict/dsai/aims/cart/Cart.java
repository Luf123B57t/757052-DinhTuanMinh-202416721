package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Cart {
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (media == null) return;
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("Added to cart: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (media == null) return;
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle());
        }
    }

    public float totalCost() {
        float sum = 0;
        for (Media m : itemsOrdered) {
            sum += m.getCost();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        if (itemsOrdered.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            // Lặp qua ObservableList
            for (int i = 0; i < itemsOrdered.size(); i++) {
                Media m = itemsOrdered.get(i);
                System.out.println((i + 1) + ". " + m.getTitle() + " - $" + m.getCost());
            }
            System.out.println("Total cost: $" + totalCost());
        }
        System.out.println("**************************************************");
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("Cart cleared.");
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}
