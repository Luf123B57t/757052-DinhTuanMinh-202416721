package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.screen.StoreScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lớp cha trừu tượng
public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;
    protected StoreScreen storeScreen;

    public AddItemToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        this.store = store;
        this.cart = cart;
        this.storeScreen = storeScreen;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        cp.add(createNorth(), BorderLayout.NORTH);


        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Add Item to Store");
        setSize(500, 400); // Kích thước nhỏ hơn
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    protected abstract JPanel createCenter();


    protected JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View Store");
        viewStoreItem.addActionListener(e -> {
            new StoreScreen(store, cart); // Mở lại StoreScreen
            this.dispose(); // Đóng màn hình hiện tại
        });
        menu.add(viewStoreItem);
        menu.add(new JMenuItem("View Cart")); // Vẫn giữ View Cart

        menuBar.add(menu);
        north.add(menuBar);
        return north;
    }


    protected void showSuccessDialog(String itemName) {
        JOptionPane.showMessageDialog(this,
                itemName + " đã được thêm vào kho hàng.",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
