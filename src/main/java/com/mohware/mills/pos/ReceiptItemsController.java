package com.mohware.mills.pos;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        listenerz.onClickListener3(namex, qtymm);
    }
    private mylistener listenerz;
    ArrayList itemzz;
    String namex, qtymm;

    public void receiptItems(ArrayList itemzz, mylistener listener) {

        this.listenerz = listener;
        this.itemzz = itemzz;

        String price = (String) itemzz.get(2);
        String item = (String) itemzz.get(0);
        String qty = (String) itemzz.get(1);
        String total = (String) itemzz.get(3);
        String stock = (String) itemzz.get(4);
        itmPrice.setText(price);
        itmName.setText(item);
        itmQty.setText(qty);
        itmAmount.setText(total);

        itmQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    itmQty.setText(oldValue);
                }
                if (itmQty.getText().equals("")) {
                    newValue = "0";
                    //itmQty.setText(newValue);
                }

                int entered = Integer.parseInt(newValue);
                int stocked = Integer.parseInt(stock);

                if (entered > stocked) {
                    itmQty.setText(oldValue);
                    System.out.println("Exceeded!!");

                } else if (entered < 0) {
                    itmQty.setText(oldValue);
                    System.out.println("no zero!!");

                } else {
                    qtymm = newValue;
                    namex = item;
                }

            }
        });

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        File logox = new File("images/dash.png");
        Image logo_image = new Image(logox.toURI().toString());
        action.setImage(logo_image);
    }

}
