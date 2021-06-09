package com.mohware.mills.pos;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ReceiptItemsController implements Initializable {

    @FXML
    private Label itmName, itmPrice, itmAmount;
    @FXML
    private ImageView action;
    @FXML
    private TextField itmQty;

    @FXML
    public void click(KeyEvent keyEvent) {
        listenerz.onClickListener3(itemzz);
    }
    private mylistener listenerz;
    ArrayList itemzz;

    public void receiptItems(ArrayList itemzz, mylistener listener) {

        this.listenerz = listener;
        this.itemzz = itemzz;

        String price = (String) itemzz.get(2);
        String item = (String) itemzz.get(0);
        String qty = (String) itemzz.get(1);
        String total = (String) itemzz.get(3);
        itmPrice.setText(price);
        itmName.setText(item);
        itmQty.setText(qty);
        itmAmount.setText(total);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        File logox = new File("images/dash.png");
        Image logo_image = new Image(logox.toURI().toString());
        action.setImage(logo_image);
    }

}
