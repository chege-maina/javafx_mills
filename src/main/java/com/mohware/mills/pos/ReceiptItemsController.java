package com.mohware.mills.pos;

import com.mohware.mills.main.main;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReceiptItemsController implements Initializable{

    @FXML
    private Label itmName, itmQty, itmPrice, itmAmount;
    @FXML
    private ImageView action;

    public void receiptItems(ArrayList itemzz) {
        
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
