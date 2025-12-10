package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;
    private StoreScreen parentScreen;


    public MediaStore(Media media, Cart cart, StoreScreen parentScreen) {
        this.media = media;
        this.cart = cart;
        this.parentScreen = parentScreen;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));


        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new AddToCartListener());
        container.add(btnAddToCart);


        if (media instanceof Playable) {
            container.add(new JButton("Play"));

        }


        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }


    private class AddToCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cart.addMedia(media);


                JOptionPane.showMessageDialog(
                        parentScreen,
                        media.getTitle() + " đã được thêm vào giỏ hàng.",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {

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
