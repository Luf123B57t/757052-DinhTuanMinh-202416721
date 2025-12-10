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
        this.store = store; // ⭐️ SỬA 1: Lưu trữ đối tượng Store

        // Thiết lập kích thước và đóng cửa sổ
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // ⭐️ SỬA 2: Lấy Menu Bar và đặt nó trước khi thêm các component khác
        JMenuBar menuBar = createCartMenuBar();
        setJMenuBar(menuBar);

        JFXPanel fxPanel = new JFXPanel();
        cp.add(fxPanel, BorderLayout.CENTER); // Đặt JFXPanel vào trung tâm

        this.setTitle("Cart");
        this.setVisible(true); // Hiển thị cửa sổ

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = null;
                try {
                    // ⭐️ SỬA 3: Thêm dấu '/' vào đầu đường dẫn FXML
                    loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml"));

                    Parent root = loader.load();

                    // Lấy Controller tự động tạo
                    CartScreenController controller = loader.getController();

                    // Truyền dữ liệu bằng Setter (BẮT BUỘC: Controller phải có các setters này)
                    controller.setCart(cart);
                    controller.setStore(store); // Controller cần có setStore()

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

        // View Store Action
        JMenuItem viewStoreItem = new JMenuItem("View Store");
        viewStoreItem.addActionListener(e -> {
            // Mở lại StoreScreen, truyền Store và Cart đã lưu
            new StoreScreen(this.store, this.cart);
            this.dispose();
        });
        menu.add(viewStoreItem);

        JMenuItem viewCartItem = new JMenuItem("View Cart");
        // View Cart (Không làm gì, vì đã ở CartScreen)
        // viewCartItem.addActionListener(e -> { });
        menu.add(viewCartItem);

        menuBar.add(menu);
        return menuBar;
    }
}
