package com.mohware.mills.dashboard;

import com.mohware.mills.model.CustHelp;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class productsList implements Initializable {

    @FXML
    private Label prodCode, prodName, prodCat, prodBp, prodSp;

    @FXML
    private Button action;
    
    @FXML
    public void click(MouseEvent mouseEvent){
        listenerz.onClickListener3(itemzz);
    }
    private mylistener listenerz;
    ArrayList itemzz;

    public void productListitems(ArrayList itemzz, mylistener listenerz) {
        this.listenerz = listenerz;
        this.itemzz = itemzz;

        String code = (String) itemzz.get(0);
        String name = (String) itemzz.get(1);
        String cat = (String) itemzz.get(2);
        String bp = (String) itemzz.get(3);
        String sp = (String) itemzz.get(4);
        prodCode.setText(code);
        prodName.setText(name);
        prodCat.setText(cat);
        prodBp.setText(bp);
        prodSp.setText(sp);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
