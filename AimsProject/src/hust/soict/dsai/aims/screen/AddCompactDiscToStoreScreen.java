package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.screen.StoreScreen;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfCost, tfArtist;

    public AddCompactDiscToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super(store, cart, storeScreen);
        setTitle("Add CD to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 dòng (4 trường + 1 nút)

        center.add(new JLabel("Title:"));
        tfTitle = new JTextField(20);
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        tfCategory = new JTextField(20);
        center.add(tfCategory);

        center.add(new JLabel("Cost:"));
        tfCost = new JTextField(20);
        center.add(tfCost);

        center.add(new JLabel("Artist:"));
        tfArtist = new JTextField(20);
        center.add(tfArtist);

        JButton btnSave = new JButton("Save CD");
        btnSave.addActionListener(new SaveCDListener());

        center.add(new JPanel()); // Ô trống
        center.add(btnSave);

        return center;
    }

    private class SaveCDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            String artist = tfArtist.getText();

            float cost = 0.0f;
            try {
                cost = Float.parseFloat(tfCost.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AddCompactDiscToStoreScreen.this,
                        "Cost phải là số.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo và thêm CompactDisc (Giả sử constructor CompactDisc có dạng: title, category, cost, artist)
            // Lưu ý: Bạn cần điều chỉnh constructor này cho phù hợp với cấu trúc CompactDisc của bạn (tracks)
            CompactDisc cd = new CompactDisc(title, category, cost, artist);
            store.addMedia(cd);

            // Hiển thị thông báo, mở lại StoreScreen và đóng cửa sổ hiện tại
            showSuccessDialog(cd.getTitle());
            // Refresh the existing StoreScreen instead of creating a new one
            if (storeScreen != null) {
                storeScreen.refresh();
            }
            dispose();
        }
    }
}
