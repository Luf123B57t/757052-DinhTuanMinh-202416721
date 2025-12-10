package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");

        // Create and add items with listeners so clicking will open the proper add screens
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            new AddBookToStoreScreen(store, cart, this);
            // keep this StoreScreen visible so Add screen can call storeScreen.refresh()
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store, cart, this);
            // keep this StoreScreen visible so Add screen can call storeScreen.refresh()
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store, cart, this);
            // keep this StoreScreen visible so Add screen can call storeScreen.refresh()
        });
        smUpdateStore.add(addDVDItem);

        menu.add(smUpdateStore);

        JMenuItem viewStoreItem = new JMenuItem("View Store");
        viewStoreItem.addActionListener(e -> {
            // reopen store screen (no-op or refresh). We'll just create a new one
            new StoreScreen(this.store, this.cart);
            this.dispose();
        });
        menu.add(viewStoreItem);

        JMenuItem viewCartItem = new JMenuItem("View Cart");
        viewCartItem.addActionListener(e -> {
            if (this.cart != null && this.store != null) {
                new CartScreen(this.cart, this.store);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cart hoặc Store chưa được khởi tạo.", "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
            }
        });
        menu.add(viewCartItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));
        cart.addActionListener(e -> {
            new CartScreen(this.cart, this.store);
            this.dispose(); // Tùy chọn: Đóng màn hình StoreScreen hiện tại
        });
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        return header;
    }

    // Trong StoreScreen.java
    JPanel createCenter() {
        JPanel center = new JPanel();

        // Show all items in a grid with 3 columns; compute rows dynamically so newly-added items appear
        ArrayList<Media> mediaInStore = this.store.getItemsInStore();
        int n = mediaInStore.size();
        int cols = 3;
        int rows = (n == 0) ? 1 : ((n + cols - 1) / cols);
        center.setLayout(new GridLayout(rows, cols, 8, 8));

        // Add a MediaStore cell for each media in store
        for (int i = 0; i < n; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), this.cart, this);
            center.add(cell);
        }

        // If there are fewer cells than the grid capacity, add empty panels to keep layout
        int capacity = rows * cols;
        for (int i = n; i < capacity; i++) {
            center.add(new JPanel());
        }

        return center;
    }

    // Public method to refresh the StoreScreen content (rebuilds north and center)
    public void refresh() {
        Container cp = getContentPane();

        cp.removeAll();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.revalidate();
        cp.repaint();
    }
}
