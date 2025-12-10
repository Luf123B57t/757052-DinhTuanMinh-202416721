package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CartScreen extends JFrame {
    private Cart cart;
    private Store store; // Cần giữ lại khai báo

    public CartScreen(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;


        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        JMenuBar menuBar = createCartMenuBar();
        setJMenuBar(menuBar);

        JFXPanel fxPanel = new JFXPanel();
        cp.add(fxPanel, BorderLayout.CENTER);
        this.setTitle("Cart");
        this.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = null;
                try {
                    loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml"));

                    Parent root = loader.load();

                    CartScreenController controller = loader.getController();

                    controller.setCart(cart);
                    controller.setStore(store);

                    fxPanel.setScene(new Scene(root));
                }
                catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi tải FXML Cart: " + e.getMessage(), "Lỗi FX", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JMenuBar createCartMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View Store");
        viewStoreItem.addActionListener(e -> {
            new StoreScreen(this.store, this.cart);
            this.dispose();
        });
        menu.add(viewStoreItem);

        JMenuItem viewCartItem = new JMenuItem("View Cart");
        menu.add(viewCartItem);

        menuBar.add(menu);
        return menuBar;
    }
}
