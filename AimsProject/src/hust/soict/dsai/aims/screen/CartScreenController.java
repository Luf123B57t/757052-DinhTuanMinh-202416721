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
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    private FilteredList<Media> filteredList;

    public CartScreenController() {
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        if (this.cart == null) return;


        filteredList = new FilteredList<>(this.cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);


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

        filteredList.setPredicate(media -> {
            // Nếu TextField trống, hiển thị tất cả
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);
            } else if (radioBtnFilterId.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);

            }

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

        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("Đặt hàng thành công!");
        info.setHeaderText("Đơn hàng đã được xử lý.");
        info.setContentText("Tổng chi phí: " + String.format("%.2f $", cart.totalCost()));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.showAndWait();


        cart.getItemsOrdered().clear();


        Alert choice = new Alert(AlertType.CONFIRMATION);
        choice.setTitle("Tiếp tục");
        choice.setHeaderText("Bạn muốn tiếp tục mua sắm?\n(Hoặc chọn Close để đóng)");
        ButtonType continueBtn = new ButtonType("Continue shopping");
        ButtonType closeBtn = ButtonType.CLOSE;
        choice.getButtonTypes().setAll(continueBtn, closeBtn);
        Optional<ButtonType> result = choice.showAndWait();
        if (result.isPresent() && result.get() == continueBtn) {
            handleViewStore(new javafx.event.ActionEvent());
        }
    }


    @FXML
    public void handleViewStore(ActionEvent event) {
        if (store == null || cart == null) return;
        SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }


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

        if (media instanceof Playable) {
            try {
                ((Playable) media).play();


                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Media Playback");
                alert.setHeaderText(null);

                String info = ((Playable) media).getPlayInfo();
                if (info == null || info.isEmpty()) {
                    info = "Now playing: " + media.getTitle();
                }
                alert.setContentText(info);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi Playback");
                alert.setHeaderText("Không thể chơi: " + media.getTitle());
                alert.setContentText("Chi tiết lỗi: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Sản phẩm này không thể chơi.");
            alert.showAndWait();
        }
    }
}
