package com.mohware.mills.pos;

import com.jfoenix.controls.JFXRippler;
import com.mohware.mills.main.main;
import com.mohware.mills.model.CustHelp;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ItemsController implements Initializable {

    @FXML
    private ImageView itemimage;
    
    @FXML
    private AnchorPane anchorP;

    @FXML
    private VBox itemVbox;

    @FXML
    private Label itemname, qty, price;
    
    @FXML
    public void click(MouseEvent mouseEvent){
        listenerz.onClickListener(itemzz);
    }

    private mylistener listenerz;
    CustHelp itemzz;

    public void setItems(String items, String path, String price1, String qty1, CustHelp itemzz, mylistener listenerz) {

        this.listenerz = listenerz;
        this.itemzz = itemzz;
        File logox = new File("images/" + path);
        Image logo_image = new Image(logox.toURI().toString());
        itemimage.setImage(logo_image);

        String prizess = itemzz.getPrice();
        itemname.setText(items);
        qty.setText(qty1 + " In Stock");
        price.setText(main.CURRENCY + prizess + "/=");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        JFXRippler rippler = new JFXRippler(itemVbox);
        anchorP.getChildren().add(rippler);
    }
}
