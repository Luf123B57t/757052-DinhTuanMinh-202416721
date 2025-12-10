package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.screen.StoreScreen;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfCost, tfDirector, tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super(store, cart, storeScreen);
        setTitle("Add DVD to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(6, 2, 10, 10));

        center.add(new JLabel("Title:"));
        tfTitle = new JTextField(20);
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        tfCategory = new JTextField(20);
        center.add(tfCategory);

        center.add(new JLabel("Cost:"));
        tfCost = new JTextField(20);
        center.add(tfCost);

        center.add(new JLabel("Director:"));
        tfDirector = new JTextField(20);
        center.add(tfDirector);

        center.add(new JLabel("Length:"));
        tfLength = new JTextField(20);
        center.add(tfLength);

        JButton btnSave = new JButton("Save DVD");
        btnSave.addActionListener(new SaveDVDListener());

        center.add(new JPanel()); // Ô trống
        center.add(btnSave);

        return center;
    }

    private class SaveDVDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            String director = tfDirector.getText();

            // Chuyển đổi và xử lý lỗi (Không cần validate theo yêu cầu bài lab)
            float cost = 0.0f;
            int length = 0;
            try {
                cost = Float.parseFloat(tfCost.getText());
                length = Integer.parseInt(tfLength.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AddDigitalVideoDiscToStoreScreen.this,
                        "Cost và Length phải là số.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo và thêm DVD
            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);

            // Hiển thị thông báo, mở lại StoreScreen và đóng cửa sổ hiện tại
            showSuccessDialog(dvd.getTitle());
            // Refresh the existing StoreScreen instead of creating a new one
            if (storeScreen != null) {
                storeScreen.refresh();
            }
            dispose();
        }
    }
}
