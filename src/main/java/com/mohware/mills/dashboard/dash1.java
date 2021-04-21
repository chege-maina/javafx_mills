package com.mohware.mills.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.mohware.mills.model.CustHelp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

public class dash1 implements DashboardView, Initializable {

    @FXML
    private Label Menu, CloseMenu;

    @FXML
    private StackPane rootpanes;

    @FXML
    private Label image_label;

    @FXML
    private Button btnAddCategory, btnCancel, btnAddUnit2, btnAddUnit1, btnImgSelect, btnImageView;

    @FXML
    private AnchorPane slider, addproducts_acpane, receiveproducts_acpane;
    @FXML
    private JFXButton products_menu, store_menu;

    @FXML
    private JFXComboBox<String> categoryCombo, supplierCombo, sellingCombo;

    @FXML
    private HBox sub_products, sub_store;

    @FXML
    private ImageView products, Exit, warehouse, procument, suppliers, x_menu, list_products, menu_icon, add_product;

    @FXML
    private ImageView receiveitems_img, managestore_img, stocksheet_img, category_img, supplier_img, sell_img;

    @FXML
    private JFXTextField prod_name, opening_bal, conversion_txt, buying_txt, selling_txt, tax_txt, max_txt, min_txt, reorder_txt;

    DashboardPresenter presenter;
    private mylistener listenerz;
    ArrayList<String> listelm;
    ArrayList<String> unitlist;
    public static String comboAdder = "";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        presenter = new DashboardPresenter(this);
        showimages();
        hideItems();
        initialView();
        unitlist = new ArrayList<>();
        listelm = new ArrayList<>();

        buttons_events();
        textField_events();
        dbaseEvents();
        Platform.runLater(() -> {

            listenerz = new mylistener() {
                @Override
                public void onClickListener() {

                    categoryCombo.getItems().clear();
                    listelm.add(comboAdder);
                    ObservableList<String> categoryList = FXCollections.observableArrayList(
                            listelm);
                    categoryCombo.setItems(categoryList);

                }

                @Override
                public void onClickListener2() {
                    supplierCombo.getItems().clear();
                    sellingCombo.getItems().clear();
                    unitlist.add(comboAdder);
                    ObservableList<String> unitList = FXCollections.observableArrayList(
                            unitlist);
                    supplierCombo.setItems(unitList);
                    sellingCombo.setItems(unitList);
                }

            };

        });

    }

    public void hideItems() {
        sub_store.setVisible(false);
        sub_products.setVisible(false);
        addproducts_acpane.setVisible(false);
        receiveproducts_acpane.setVisible(false);

    }

    public void initialView() {
        sub_store.setVisible(false);
        sub_products.setVisible(true);
        addproducts_acpane.setVisible(true);
        receiveproducts_acpane.setVisible(false);

    }

    public void buttons_events() {
        btnImageView.setOnMouseClicked(mouseEvent -> {
            try {
                String picPath = image_label.getText().toString();

                Stage dialogStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewImage.fxml"));
                AnchorPane root = (AnchorPane) loader.load();
                dialogStage.initStyle(StageStyle.UNDECORATED);
                picController controller = (picController) loader.getController();
                controller.setPic(picPath);
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        btnImgSelect.setOnMouseClicked(mouseEvent -> {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Select Product Image");
            Stage stage = (Stage) rootpanes.getScene().getWindow();

            File file = filechooser.showOpenDialog(stage);

            image_label.setText(file.getPath());

        });
        btnAddCategory.setOnMouseClicked(mouseEvent -> {

            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/fxml/addCategory.fxml"));
                DialogPane addCategory = fxmlloader.load();

                Dialog<ButtonType> addCatDialog = new Dialog();
                addCatDialog.setDialogPane(addCategory);
                addCatDialog.initStyle(StageStyle.UNDECORATED);

                categoryController catcontroller = fxmlloader.getController();
                catcontroller.setItems(listenerz);
                Optional<ButtonType> clickedCatButton = addCatDialog.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnAddUnit2.setOnMouseClicked(mouseEvent -> {

            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/fxml/addUnit.fxml"));
                DialogPane addCategory = fxmlloader.load();

                Dialog<ButtonType> addCatDialog = new Dialog();
                addCatDialog.setDialogPane(addCategory);
                addCatDialog.initStyle(StageStyle.UNDECORATED);

                unitController catcontroller = fxmlloader.getController();
                catcontroller.setItems(listenerz);
                Optional<ButtonType> clickedCatButton = addCatDialog.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnAddUnit1.setOnMouseClicked(mouseEvent -> {

            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/fxml/addUnit.fxml"));
                DialogPane addCategory = fxmlloader.load();

                Dialog<ButtonType> addCatDialog = new Dialog();
                addCatDialog.setDialogPane(addCategory);
                addCatDialog.initStyle(StageStyle.UNDECORATED);

                unitController catcontroller = fxmlloader.getController();
                catcontroller.setItems(listenerz);
                Optional<ButtonType> clickedCatButton = addCatDialog.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCancel.setOnMouseClicked(mouseEvent -> {
            categoryCombo.getItems().clear();
            supplierCombo.getItems().clear();
            sellingCombo.getItems().clear();
            combobox_events();

        });
        Exit.setOnMouseClicked(mouseEvent -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root, 900, 600));
                stage.show();
                ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        products_menu.setOnMouseClicked(mouseEvent -> {
            hideItems();
            sub_products.setVisible(true);
            addproducts_acpane.setVisible(true);

        });
        store_menu.setOnMouseClicked(mouseEvent -> {
            hideItems();
            sub_store.setVisible(true);
            receiveproducts_acpane.setVisible(true);

        });
        Menu.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                CloseMenu.setVisible(true);
            });
        });

        CloseMenu.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                CloseMenu.setVisible(false);
            });
        });
    }

    public void textField_events() {
        opening_bal.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    opening_bal.setText(oldValue);
                }
            }
        });

        conversion_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    conversion_txt.setText(oldValue);
                }
            }
        });

        buying_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    buying_txt.setText(oldValue);
                }
            }
        });

        selling_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    selling_txt.setText(oldValue);
                }
            }
        });

        tax_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tax_txt.setText(oldValue);
                }
            }
        });

        max_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    max_txt.setText(oldValue);
                }
            }
        });
        min_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    min_txt.setText(oldValue);
                }
            }
        });
        reorder_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    reorder_txt.setText(oldValue);
                }
            }
        });
    }

    public void combobox_events() {
        supplierCombo.getItems().clear();
        sellingCombo.getItems().clear();

    }

    private void addItem() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        /*jsonObject.put("orderno", orderno);
        jsonObject.put("date", dates);
        jsonObject.put("time", times);
        jsonObject.put("total", total);
        jsonObject.put("waiter", waiter);
        jsonObject.put("status", status);
        jsonObject.put("comment", comment);
        jsonObject.put("chef", chef);*/
        jsonArray.put(jsonObject);
    }

    public void showimages() {
        File menu_close = new File("images/x.png");
        Image close_menu = new Image(menu_close.toURI().toString());
        x_menu.setImage(close_menu);

        menu_close = new File("images/cash.png");
        close_menu = new Image(menu_close.toURI().toString());
        products.setImage(close_menu);

        menu_close = new File("images/house-fill.png");
        close_menu = new Image(menu_close.toURI().toString());
        add_product.setImage(close_menu);

        menu_close = new File("images/list-check.png");
        close_menu = new Image(menu_close.toURI().toString());
        list_products.setImage(close_menu);

        menu_close = new File("images/menu.png");
        close_menu = new Image(menu_close.toURI().toString());
        menu_icon.setImage(close_menu);

        menu_close = new File("images/shop.png");
        close_menu = new Image(menu_close.toURI().toString());
        warehouse.setImage(close_menu);

        menu_close = new File("images/speedometer.png");
        close_menu = new Image(menu_close.toURI().toString());
        suppliers.setImage(close_menu);

        menu_close = new File("images/house-fill.png");
        close_menu = new Image(menu_close.toURI().toString());
        procument.setImage(close_menu);

        menu_close = new File("images/house-fill.png");
        close_menu = new Image(menu_close.toURI().toString());
        receiveitems_img.setImage(close_menu);

        menu_close = new File("images/list-check.png");
        close_menu = new Image(menu_close.toURI().toString());
        managestore_img.setImage(close_menu);

        menu_close = new File("images/list-check.png");
        close_menu = new Image(menu_close.toURI().toString());
        stocksheet_img.setImage(close_menu);

        menu_close = new File("images/x-circle-fill.png");
        close_menu = new Image(menu_close.toURI().toString());
        Exit.setImage(close_menu);

        menu_close = new File("images/plus.png");
        close_menu = new Image(menu_close.toURI().toString());
        category_img.setImage(close_menu);

        menu_close = new File("images/plus.png");
        close_menu = new Image(menu_close.toURI().toString());
        supplier_img.setImage(close_menu);

        menu_close = new File("images/plus.png");
        close_menu = new Image(menu_close.toURI().toString());
        sell_img.setImage(close_menu);

        Menu.setVisible(false);
    }

    private void dbaseEvents() {
        presenter.getCategory();
        presenter.getUnit();

    }

    public void infodialog(String btnText, String msg) {
        JFXDialogLayout dialoglayout = new JFXDialogLayout();
        JFXButton button = new JFXButton(btnText);
        JFXDialog dialog = new JFXDialog(rootpanes, dialoglayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
        });
        dialoglayout.setBody(new Text(msg));
        dialoglayout.setActions(button);
        dialog.show();

    }

    @Override
    public void onErrorLoading(String message) {
        Platform.runLater(() -> {
            infodialog("Okay", message);
        });

    }

    @Override
    public void onAddSuccess(String message) {
    }

    @Override
    public void onAddError(String message) {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void onGetUnitResult(List<CustHelp> unit) {
        int x = unit.size();
        supplierCombo.getItems().clear();
        sellingCombo.getItems().clear();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < x; i++) {
                    unitlist.add(unit.get(i).getCategory());

                }
                ObservableList<String> listLeagues = FXCollections.observableArrayList(
                        unitlist);
                supplierCombo.setItems(listLeagues);
                sellingCombo.setItems(listLeagues);
            }
        });

    }

    @Override
    public void onGetResult(List<CustHelp> category) {
        int x = category.size();
        categoryCombo.getItems().clear();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < x; i++) {
                    listelm.add(category.get(i).getCategory());

                }
                ObservableList<String> categoryList = FXCollections.observableArrayList(
                        listelm);
                categoryCombo.setItems(categoryList);
            }
        });

    }

    @Override
    public void onUnitAddSuccess(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
