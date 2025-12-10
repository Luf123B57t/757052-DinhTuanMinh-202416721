package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.screen.StoreScreen;
import javax.swing.SwingUtilities;

public class Aims {
    public static void main(String[] args) {

        // 1. CHUẨN BỊ DỮ LIỆU CỐ ĐỊNH (SETUP INITIAL DATA)

        Store store = new Store();
        Cart cart = new Cart();


        // DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 121, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 15.99f);

        // Books
        Book book1 = new Book("Java Programming", "Programming", 45.50f);
        Book book2 = new Book("Design Patterns", "Programming", 65.00f);
        Book book3 = new Book("Clean Code", "Programming", 50.00f);

        // CDs (Giả sử constructor của bạn có thêm Artist)
        CompactDisc cd1 = new CompactDisc("Divide", "Pop", 10.00f, "Ed Sheeran");
        CompactDisc cd2 = new CompactDisc("Thriller", "Pop", 15.00f, "Michael Jackson");
        CompactDisc cd3 = new CompactDisc("Dark Side", "Rock", 30.00f, "Pink Floyd");

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);
        store.addMedia(cd1);
        store.addMedia(dvd3);
        store.addMedia(book2);
        store.addMedia(cd2);
        store.addMedia(book3);
        store.addMedia(cd3);

        // 2. CHẠY GIAO DIỆN GUI (LAUNCH APPLICATION)

        SwingUtilities.invokeLater(() -> {
            // Mở màn hình StoreScreen, truyền Store và Cart
            new StoreScreen(store, cart);
        });
    }
}
