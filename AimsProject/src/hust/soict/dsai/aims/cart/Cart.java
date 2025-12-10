package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Cart {
    // ⭐️ SỬA LỖI: Chỉ dùng một ObservableList duy nhất để lưu trữ dữ liệu
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        // ⚠️ Lưu ý: Thêm logic kiểm tra giới hạn (LimitExceededException) nếu cần
        if (media == null) return;

        // Sử dụng ObservableList để kiểm tra sự tồn tại và thêm vào
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("Added to cart: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (media == null) return;

        // Chỉ cần thao tác trên ObservableList
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle());
        }
    }

    public float totalCost() {
        float sum = 0;
        // Lặp qua ObservableList
        for (Media m : itemsOrdered) {
            sum += m.getCost();
        }
        return sum;
    }

    // Xóa phương thức getItems() (ArrayList) hoặc thay đổi nó để trả về ObservableList

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
        // Chỉ cần xóa ObservableList
        itemsOrdered.clear();
        System.out.println("Cart cleared.");
    }

    // ⭐️ SỬA: Getter giờ trả về ObservableList chính của giỏ hàng
    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}
