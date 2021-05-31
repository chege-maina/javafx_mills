package com.mohware.mills.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.mohware.mills.model.CustHelp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
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
import javafx.scene.layout.VBox;
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
    private VBox lstItem;

    @FXML
    private StackPane rootpanes;

    @FXML
    private Label image_label, prod_name_label, opening_bal_label, conversion_txt_label, min_txt_label, buying_txt_label, max_txt_label, tax_txt_label, selling_txt_label;

    @FXML
    private Label reorder_txt_label, image_error_label, categoryCombolabel, sellingCombolabel, supplierCombolabel;

    @FXML
    private Label edtProdName, edtProdCode, edtStatus;

    @FXML
    private Label edtOpenbalLbl, edtConversionLbl, edtBsLbl, edtSpLbl, edtTaxLbl, edtMaxLbl, edtMinLbl, edtReorderLbl, edtCatLabel, edtSupLabel, edtSelLabel;

    @FXML
    private Button btnAddCategory, btnCancel, btnAddUnit2, btnAddUnit1, btnImgSelect, btnImageView, btnSave, edtSave, edtActivate, edtDeactivate;

    @FXML
    private AnchorPane slider, addproducts_acpane, receiveproducts_acpane, productList, editProduct;
    @FXML
    private JFXButton products_menu, store_menu, list_prod_menu, new_product;

    @FXML
    private JFXComboBox<String> categoryCombo, supplierCombo, sellingCombo, edtCatCombo, edtSupCombo, edtSelCombo;

    @FXML
    private HBox sub_products, sub_store;

    @FXML
    private ImageView products, Exit, warehouse, procument, suppliers, x_menu, list_products, menu_icon, add_product;

    @FXML
    private ImageView receiveitems_img, managestore_img, stocksheet_img, category_img, supplier_img, sell_img;

    @FXML
    private JFXTextField prod_name, opening_bal, conversion_txt, buying_txt, selling_txt, tax_txt, max_txt, min_txt, reorder_txt;

    @FXML
    private JFXTextField edtOpenbal, edtBs, edtSp, edtTax, edtMax, edtMin, edtReorder, edtConversion;

    DashboardPresenter presenter;
    private mylistener listenerz;
    ArrayList<ArrayList<String>> receiptlist1;
    ArrayList<String> listelm;
    ArrayList<String> unitlist;
    public static String comboAdder = "";
    private String checked = "";
    private String encoded_image;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        presenter = new DashboardPresenter(this);
        showimages();
        hideItems();
        initialView();
        receiptlist1 = new ArrayList<>();
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
                    edtCatCombo.getItems().clear();
                    listelm.add(comboAdder);
                    ObservableList<String> categoryList = FXCollections.observableArrayList(
                            listelm);
                    categoryCombo.setItems(categoryList);
                    edtCatCombo.setItems(categoryList);

                }

                @Override
                public void onClickListener2() {
                    supplierCombo.getItems().clear();
                    sellingCombo.getItems().clear();
                    edtSelCombo.getItems().clear();
                    edtSupCombo.getItems().clear();
                    unitlist.add(comboAdder);
                    ObservableList<String> unitList = FXCollections.observableArrayList(
                            unitlist);
                    supplierCombo.setItems(unitList);
                    sellingCombo.setItems(unitList);
                    edtSelCombo.setItems(unitList);
                    edtSupCombo.setItems(unitList);
                }

                @Override
                public void onClickListener3(ArrayList prod) {

                    selectedProduct(prod);

                }

            };

        });

    }

    private void selectedProduct(ArrayList prod) {

        hideItems();
        sub_products.setVisible(true);
        editProduct.setVisible(true);
        String code = prod.get(0).toString();
        presenter.editProd(code);

    }

    private void validate() {
        if (!txtFldVald("Product Name Cannot be left Empty!!", prod_name_label, prod_name)
                | !txtFldVald("Opening Balance Cannot be left Empty!!", opening_bal_label, opening_bal)
                | !txtFldVald("Conversion Unit Cannot be left Empty!!", conversion_txt_label, conversion_txt)
                | !txtFldVald("Buying Price Cannot be left Empty!!", buying_txt_label, buying_txt)
                | !txtFldVald("Selling Price Cannot be left Empty!!", selling_txt_label, selling_txt)
                | !txtFldVald("Product Tax Cannot be left Empty!!", tax_txt_label, tax_txt)
                | !txtFldVald("Maximum Lavel Cannot be left Empty!!", max_txt_label, max_txt)
                | !txtFldVald("Minimum Level Cannot be left Empty!!", min_txt_label, min_txt)
                | !txtFldVald("Stock Reorder Level Cannot be left Empty!!", reorder_txt_label, reorder_txt)
                | !comboVald("Please Select the Product Category!!", categoryCombolabel, categoryCombo)
                | !comboVald("Please Select the Product Supplier Unit!!", supplierCombolabel, supplierCombo)
                | !comboVald("Please Select the Product Selling Unit!!", sellingCombolabel, sellingCombo)
                | !labelVald("Please Select Product Image!!", image_error_label, image_label)) {
            return;
        }
        addItem();

    }

    private void validateEdit() {
        if (!txtFldVald("Opening Balance Cannot be left Empty!!", edtOpenbalLbl, edtOpenbal)
                | !txtFldVald("Conversion Unit Cannot be left Empty!!", edtConversionLbl, edtConversion)
                | !txtFldVald("Buying Price Cannot be left Empty!!", edtBsLbl, edtBs)
                | !txtFldVald("Selling Price Cannot be left Empty!!", edtSpLbl, edtSp)
                | !txtFldVald("Product Tax Cannot be left Empty!!", edtTaxLbl, edtTax)
                | !txtFldVald("Maximum Lavel Cannot be left Empty!!", edtMaxLbl, edtMax)
                | !txtFldVald("Minimum Level Cannot be left Empty!!", edtMinLbl, edtMin)
                | !txtFldVald("Stock Reorder Level Cannot be left Empty!!", edtReorderLbl, edtReorder)
                | !comboVald("Please Select the Product Category!!", edtCatLabel, edtCatCombo)
                | !comboVald("Please Select the Product Supplier Unit!!", edtSupLabel, edtSupCombo)
                | !comboVald("Please Select the Product Selling Unit!!", edtSelLabel, edtSelCombo)) {
            return;
        }
        editItem();

    }

    private boolean txtFldVald(String message, Label theLabel, JFXTextField theTxt) {
        String val = theTxt.getText();
        if (val.isEmpty()) {
            theLabel.setText(message);
            theTxt.getStyleClass().add("textInputError");
            return false;
        } else {
            theLabel.setText("");
            theTxt.getStyleClass().add("textInput");
            return true;
        }
    }

    private boolean labelVald(String message, Label theLabel, Label theTxt) {
        String val = theTxt.getText();
        if (val.isEmpty()) {
            theLabel.setText(message);
            theTxt.getStyleClass().add("textInputError");
            return false;
        } else {
            theLabel.setText("");
            theTxt.getStyleClass().add("textInput");
            return true;
        }
    }

    private boolean comboVald(String message, Label theLabel, JFXComboBox<String> theTxt) {
        boolean isMyComboBoxEmpty = theTxt.getSelectionModel().isEmpty();
        if (isMyComboBoxEmpty) {
            theLabel.setText(message);
            theTxt.getStyleClass().add("textInputError");
            return false;
        } else {
            theLabel.setText("");
            theTxt.getStyleClass().add("textInput");
            return true;
        }
    }

    public void hideItems() {
        sub_store.setVisible(false);
        sub_products.setVisible(false);
        addproducts_acpane.setVisible(false);
        productList.setVisible(false);
        editProduct.setVisible(false);
        receiveproducts_acpane.setVisible(false);

    }

    private void clearMe() {
        prod_name.setText("");
        opening_bal.setText("");
        conversion_txt.setText("");
        buying_txt.setText("");
        selling_txt.setText("");
        tax_txt.setText("");
        max_txt.setText("");
        min_txt.setText("");
        reorder_txt.setText("");
        reorder_txt_label.setText("");
        image_error_label.setText("");
        categoryCombolabel.setText("");
        sellingCombolabel.setText("");
        supplierCombolabel.setText("");
        image_label.setText("");
        prod_name_label.setText("");
        opening_bal_label.setText("");
        conversion_txt_label.setText("");
        min_txt_label.setText("");
        buying_txt_label.setText("");
        max_txt_label.setText("");
        tax_txt_label.setText("");
        selling_txt_label.setText("");
        categoryCombo.getItems().clear();
        supplierCombo.getItems().clear();
        sellingCombo.getItems().clear();
        encoded_image = "";
    }

    public void initialView() {
        sub_products.setVisible(true);
        addproducts_acpane.setVisible(true);

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
            try {
                FileChooser filechooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                filechooser.getExtensionFilters().add(extFilter);
                filechooser.setTitle("Select Product Image");
                Stage stage = (Stage) rootpanes.getScene().getWindow();

                File file = filechooser.showOpenDialog(stage);

                image_label.setText(file.getPath());

                File filex = new File(image_label.getText().toString());
                byte[] bytes = new byte[(int) filex.length()];
                FileInputStream fis = new FileInputStream(filex);
                fis.read(bytes);
                fis.close();
                encoded_image = Base64.getEncoder().encodeToString(bytes);
                //System.out.print(encoded_image);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
            clearMe();
            combobox_events();

        });
        edtActivate.setOnMouseClicked(mouseEvent -> {
            presenter.itemStatus("Active", edtProdCode.getText());

        });
        edtDeactivate.setOnMouseClicked(mouseEvent -> {
            presenter.itemStatus("Inactive", edtProdCode.getText());

        });
        btnSave.setOnMouseClicked(mouseEvent -> {
            validate();

        });
        edtSave.setOnMouseClicked(mouseEvent -> {
            validateEdit();

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
        new_product.setOnMouseClicked(mouseEvent -> {
            hideItems();
            sub_products.setVisible(true);
            addproducts_acpane.setVisible(true);

        });
        list_prod_menu.setOnMouseClicked(mouseEvent -> {
            hideItems();
            sub_products.setVisible(true);
            productList.setVisible(true);
            presenter.listProducts();

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
        ObservableList<String> unitList = FXCollections.observableArrayList(
                unitlist);
        supplierCombo.setItems(unitList);
        sellingCombo.setItems(unitList);
        edtSupCombo.setItems(unitList);
        edtSelCombo.setItems(unitList);
        ObservableList<String> categoryList = FXCollections.observableArrayList(
                listelm);
        categoryCombo.setItems(categoryList);
        edtCatCombo.setItems(categoryList);

    }

    private void addItem() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product", prod_name.getText());
        jsonObject.put("category", categoryCombo.getValue());
        jsonObject.put("opening_bal", opening_bal.getText());
        jsonObject.put("supply_unit", supplierCombo.getValue());
        jsonObject.put("conversion", conversion_txt.getText());
        jsonObject.put("sell_unit", sellingCombo.getValue());
        jsonObject.put("buying_price", buying_txt.getText());
        jsonObject.put("selling_price", selling_txt.getText());
        jsonObject.put("tax", tax_txt.getText());
        jsonObject.put("max", max_txt.getText());
        jsonObject.put("min", min_txt.getText());
        jsonObject.put("reorder", reorder_txt.getText());
        jsonObject.put("image", encoded_image);
        jsonArray.put(jsonObject);
        presenter.addItems(String.valueOf(jsonArray));
    }

    private void editItem() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", edtProdCode.getText());
        jsonObject.put("category", edtCatCombo.getValue());
        jsonObject.put("opening_bal", edtOpenbal.getText());
        jsonObject.put("supply_unit", edtSupCombo.getValue());
        jsonObject.put("conversion", edtConversion.getText());
        jsonObject.put("sell_unit", edtSelCombo.getValue());
        jsonObject.put("buying_price", edtBs.getText());
        jsonObject.put("selling_price", edtSp.getText());
        jsonObject.put("tax", edtTax.getText());
        jsonObject.put("max", edtMax.getText());
        jsonObject.put("min", edtMin.getText());
        jsonObject.put("reorder", edtReorder.getText());
        jsonArray.put(jsonObject);
        presenter.editItem(String.valueOf(jsonArray));

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

    private void listProducts() {
        int totsize = receiptlist1.size();
        for (int i = 0; i < totsize; i++) {
            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/fxml/Listitem.fxml"));

                HBox hBox = fxmlloader.load();
                productsList itemscontroller = fxmlloader.getController();
                itemscontroller.productListitems(receiptlist1.get(i), listenerz);
                lstItem.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void infodialog(String btnText, String msg) {
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
        Platform.runLater(() -> {
            infodialog("Okay", message);
            clearMe();
            combobox_events();
        });

    }

    @Override
    public void onAddError(String message) {
        Platform.runLater(() -> {
            infodialog("Okay", message);
        });
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
        edtSupCombo.getItems().clear();
        edtSelCombo.getItems().clear();
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
                edtSelCombo.setItems(listLeagues);
                edtSupCombo.setItems(listLeagues);
            }
        });

    }

    @Override
    public void onGetResult(List<CustHelp> category) {
        int x = category.size();
        categoryCombo.getItems().clear();
        edtCatCombo.getItems().clear();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < x; i++) {
                    listelm.add(category.get(i).getCategory());

                }
                ObservableList<String> categoryList = FXCollections.observableArrayList(
                        listelm);
                categoryCombo.setItems(categoryList);
                edtCatCombo.setItems(categoryList);
            }
        });

    }

    @Override
    public void onUnitAddSuccess(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProductsList(List<CustHelp> lstProd) {
        Platform.runLater(() -> {
            int totsize = lstProd.size();
            receiptlist1.clear();
            lstItem.getChildren().clear();
            for (int i = 0; i < totsize; i++) {
                ArrayList<String> listelm = new ArrayList<>();
                listelm.add(lstProd.get(i).getProduct_code());
                listelm.add(lstProd.get(i).getProduct_name());
                listelm.add(lstProd.get(i).getCategory());
                listelm.add(lstProd.get(i).getBuying_price());
                listelm.add(lstProd.get(i).getSelling_price());
                receiptlist1.add(listelm);
            }
            listProducts();
        });
    }

    @Override
    public void editprod(List<CustHelp> Prod) {
        Platform.runLater(() -> {
            edtProdCode.setText(Prod.get(0).getProduct_code());
            edtProdName.setText(Prod.get(0).getProduct_name());
            edtStatus.setText(Prod.get(0).getStatus());
            edtOpenbal.setText(Prod.get(0).getOpening_bal());
            edtBs.setText(Prod.get(0).getBuying_price());
            edtSp.setText(Prod.get(0).getSelling_price());
            edtTax.setText(Prod.get(0).getTax());
            edtMax.setText(Prod.get(0).getMax());
            edtMin.setText(Prod.get(0).getMin());
            edtReorder.setText(Prod.get(0).getReorder());
            edtConversion.setText(Prod.get(0).getConversion());
            edtCatCombo.getItems().clear();
            edtSupCombo.getItems().clear();
            edtSelCombo.getItems().clear();
            combobox_events();

        });
    }

    @Override
    public void onUpdateSuccess(String message) {
        Platform.runLater(() -> {
            infodialog("Okay", message);
            hideItems();
            sub_products.setVisible(true);
            productList.setVisible(true);
            presenter.listProducts();
        });
    }
}
