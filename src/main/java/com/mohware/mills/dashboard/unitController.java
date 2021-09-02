package com.mohware.mills.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.mohware.mills.model.CustHelp;
import com.mohware.mills.model.RecModel;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class unitController implements Initializable, DashboardView {

    private mylistener listenerz;
    AddCategoryPresenter presenter;

    @FXML
    private StackPane rootspanes;

    @FXML
    private ImageView Exit;

    @FXML
    private Label catLabel;

    @FXML
    private JFXTextField name;

    @FXML
    private Button save;

    private String checked = "";

    @FXML
    public void click(MouseEvent mouseEvent) {

        if (!ValCat()) {
            return;
        }
        String catname = name.getText().toString();
        presenter.addUnit(catname);

        Platform.runLater(() -> {
            if (checked.equals("okay")) {
                dash1.comboAdder = catname;
                listenerz.onClickListener2();
                final Node source = (Node) mouseEvent.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void setItems(mylistener listenerz) {

        this.listenerz = listenerz;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showimages();
        presenter = new AddCategoryPresenter(this);
        Exit.setOnMouseClicked(mouseEvent -> {
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();

        });
    }

    public void showimages() {

        File actionx2 = new File("images/x-circle-fill.png");
        Image logo_image = new Image(actionx2.toURI().toString());
        Exit.setImage(logo_image);
    }

    private boolean ValCat() {
        String val = name.getText().toString();
        if (val.isEmpty()) {
            String message = "Unit name cannot be left empty!!";
            catLabel.setText(message);
            name.getStyleClass().add("textInputError");
            return false;
        } else {
            catLabel.setText("");
            name.getStyleClass().add("textInput");
            return true;
        }
    }

    @Override
    public void onGetResult(List<CustHelp> customers) {
    }

    @Override
    public void onErrorLoading(String message) {
    }

    @Override
    public void onAddSuccess(String message) {
        Platform.runLater(() -> {
            checked = "okay";
        });
    }

    @Override
    public void onAddError(String message) {
        Platform.runLater(() -> {
            catLabel.setText(message);
            name.getStyleClass().add("textInputError");
        });

    }

    @Override
    public void hideProgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showProgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onGetUnitResult(List<CustHelp> unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onUnitAddSuccess(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProductsList(List<CustHelp> lstProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editprod(List<CustHelp> Prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void onUpdateSuccess(String message) {
       
    }

    @Override
    public void loadDispatchList(List<RecModel> body) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recItems(List<CustHelp> recitems) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDispatch(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
