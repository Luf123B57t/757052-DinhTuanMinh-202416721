package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// ⭐️ CẦN IMPORT THÊM LỚP NGOẠI LỆ CỦA BẠN (Ví dụ: PlayerException, LimitExceededException)
// import hust.soict.dsai.aims.exception.LimitExceededException;
// import hust.soict.dsai.aims.exception.PlayerException;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;
    private StoreScreen parentScreen; // Dùng để hiển thị hộp thoại

    // ✅ HÀM TẠO CHÍNH - NHẬN VÀ LƯU TRỮ CART
    public MediaStore(Media media, Cart cart, StoreScreen parentScreen) {
        this.media = media;
        this.cart = cart;
        this.parentScreen = parentScreen;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Thiết lập UI cơ bản
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // ⭐️ NÚT ADD TO CART VÀ LISTENER
        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new AddToCartListener());
        container.add(btnAddToCart);

        // NÚT PLAY (Chưa có Listener nếu bạn chỉ làm Add to Cart)
        if (media instanceof Playable) {
            container.add(new JButton("Play"));
            // Cần thêm Listener cho nút Play sau
        }

        // Add các thành phần vào JPanel chính
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    // ⭐️ INNER CLASS: Xử lý sự kiện ADD TO CART
    private class AddToCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cart.addMedia(media); // Gọi phương thức thêm vào Cart

                // Thông báo thành công
                JOptionPane.showMessageDialog(
                        parentScreen,
                        media.getTitle() + " đã được thêm vào giỏ hàng.",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) { // Bắt LimitExceededException
                // Thông báo lỗi
                JOptionPane.showMessageDialog(
                        parentScreen,
                        ex.getMessage(),
                        "Lỗi thêm vào giỏ hàng",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
