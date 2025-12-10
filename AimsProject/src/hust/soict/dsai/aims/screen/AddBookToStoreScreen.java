package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfCost;

    public AddBookToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super(store, cart, storeScreen);
        setTitle("Add Book to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(4, 2, 10, 10));

        center.add(new JLabel("Title:"));
        tfTitle = new JTextField(20);
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        tfCategory = new JTextField(20);
        center.add(tfCategory);

        center.add(new JLabel("Cost:"));
        tfCost = new JTextField(20);
        center.add(tfCost);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new SaveBookListener());
        center.add(new JPanel()); // Ô trống
        center.add(btnSave);

        return center;
    }

    private class SaveBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy dữ liệu (Không cần validate)
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText()); // ⚠️ Không validate

            // Tạo và thêm Book
            Book book = new Book(title, category, cost);
            store.addMedia(book);

            // Hiển thị thông báo và quay lại StoreScreen (sau khi dispose)
            showSuccessDialog(book.getTitle());
            // Refresh the existing StoreScreen instead of creating a new one
            if (storeScreen != null) {
                storeScreen.refresh();
            }
            dispose(); // Đóng màn hình AddBook
        }
    }
}
