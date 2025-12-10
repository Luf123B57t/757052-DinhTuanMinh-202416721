package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javax.swing.SwingUtilities;
import java.util.Optional;
import javafx.scene.control.ButtonType;

public class CartScreenController {
    private Cart cart;
    private Store store;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId; // Theo dõi trạng thái chọn By ID

    @FXML
    private RadioButton radioBtnFilterTitle; // Theo dõi trạng thái chọn By Title

    // Thêm thuộc tính để lưu trữ danh sách đã lọc
    private FilteredList<Media> filteredList;

    public CartScreenController() {
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        if (this.cart == null) return;

        // create filtered list from cart items and attach to table
        filteredList = new FilteredList<>(this.cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);

        // update total when cart changes
        this.cart.getItemsOrdered().addListener((ListChangeListener<Media>) c -> updateTotalCost());
        updateTotalCost();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @FXML
    public void initialize() {
        // Configure table columns (safe: only touches UI nodes)
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Note: don't access `cart` here because controller may be initialized before main app sets it

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>(){
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue){
                if(newValue != null){
                    updateButtonBar(newValue);
                }
            }
        });

        tfFilter.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                showFilteredMedia(newValue);
            }

        });
    }

    public void showFilteredMedia(String newValue) {
        if (filteredList == null) return; // not ready yet

        // newValue: Văn bản hiện tại trong TextField

        // Tạo Predicate mới
        filteredList.setPredicate(media -> {
            // Nếu TextField trống, hiển thị tất cả
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Chuyển văn bản tìm kiếm và giá trị Media về chữ thường để so sánh không phân biệt chữ hoa/thường
            String lowerCaseFilter = newValue.toLowerCase();

            // Kiểm tra tiêu chí lọc (By Title hoặc By ID)
            if (radioBtnFilterTitle.isSelected()) {
                // Lọc theo Title
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);
            } else if (radioBtnFilterId.isSelected()) {
                // Lọc theo ID (Giả sử Media có phương thức getId() trả về String hoặc có thể ép kiểu)
                // Lưu ý: ID thường là int, bạn có thể cần chuyển nó sang String trước khi so sánh
                // Ví dụ:
                // return String.valueOf(media.getId()).contains(lowerCaseFilter);

                // Do bạn chưa cung cấp cấu trúc Media, ta dùng cách đơn giản:
                // Lọc theo tiêu chí nào đó nếu ID được chọn (ví dụ: title tạm thời)
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);

            }

            // Mặc định (Nếu không có tiêu chí nào được chọn)
            return false;
        });
    }

    void updateButtonBar(Media media){
        btnRemove.setVisible(true);
        if(media instanceof Playable){
            btnPlay.setVisible(true);
        }
        else{
            btnPlay.setVisible(false);
        }
    }
    private void updateTotalCost() {
        if (lblTotal != null && cart != null) {
            lblTotal.setText(String.format("%.2f $", cart.totalCost()));
        }
    }

    @FXML
    public void btnPlaceOrderPressed() { // Liên kết với onAction của Place Order Button
        if (cart == null || cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Đặt hàng thất bại");
            alert.setHeaderText(null);
            alert.setContentText("Giỏ hàng trống. Vui lòng thêm sản phẩm.");
            alert.showAndWait();
            return;
        }

        // Xử lý logic đặt hàng: thông báo thành công và xóa giỏ hàng
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("Đặt hàng thành công!");
        info.setHeaderText("Đơn hàng đã được xử lý.");
        info.setContentText("Tổng chi phí: " + String.format("%.2f $", cart.totalCost()));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.showAndWait();

        // Xóa tất cả các mục khỏi giỏ hàng
        cart.getItemsOrdered().clear();

        // Cho phép người dùng lựa chọn tiếp tục mua sắm hoặc đóng
        Alert choice = new Alert(AlertType.CONFIRMATION);
        choice.setTitle("Tiếp tục");
        choice.setHeaderText("Bạn muốn tiếp tục mua sắm?\n(Hoặc chọn Close để đóng)");
        ButtonType continueBtn = new ButtonType("Continue shopping");
        ButtonType closeBtn = ButtonType.CLOSE;
        choice.getButtonTypes().setAll(continueBtn, closeBtn);
        Optional<ButtonType> result = choice.showAndWait();
        if (result.isPresent() && result.get() == continueBtn) {
            // Mở Store để tiếp tục mua sắm
            handleViewStore(new javafx.event.ActionEvent());
        }
    }

    // Menu action from FXML: View Store (open Swing StoreScreen so user can continue shopping)
    @FXML
    public void handleViewStore(ActionEvent event) {
        if (store == null || cart == null) return;
        SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }

    // Menu action from FXML: View Cart (open a CartScreen window)
    @FXML
    public void handleViewCart(ActionEvent event) {
        if (store == null || cart == null) return;
        SwingUtilities.invokeLater(() -> new CartScreen(cart, store));
    }

    @FXML
    public void btnRemovePressed(javafx.event.ActionEvent actionEvent) {
        if (cart == null) return;
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) cart.removeMedia(media);
    }

    @FXML
    public void btnPlayPressed(ActionEvent actionEvent) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();

        // 1. Kiểm tra nếu đối tượng Media là Playable
        if (media instanceof Playable) {
            try {
                // 2. Gọi phương thức play()
                ((Playable) media).play();

                // 3. Hiển thị Alert nếu Play thành công (Thông báo chơi media)
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Media Playback");
                alert.setHeaderText(null);

                // Use media.getPlayInfo() if available
                String info = ((Playable) media).getPlayInfo();
                if (info == null || info.isEmpty()) {
                    info = "Now playing: " + media.getTitle();
                }
                alert.setContentText(info);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();

            } catch (Exception e) {
                // 4. Bắt PlayerException (hoặc Exception chung nếu PlayerException chưa được định nghĩa)
                // Hiển thị Alert lỗi (Ví dụ: Illegal DVD Length)
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi Playback");
                alert.setHeaderText("Không thể chơi: " + media.getTitle());
                alert.setContentText("Chi tiết lỗi: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // Trường hợp không phải Playable (Không xảy ra nếu logic updateButtonBar() đúng)
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Sản phẩm này không thể chơi.");
            alert.showAndWait();
        }
    }
}
