/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohware.mills.dashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author mohware
 */
public class dispRecItems implements Initializable{
    
    @FXML
    private Label itmName, itmQty;
    
    ArrayList itemzz;
    public void ReceiptList(ArrayList itemzz) {

        this.itemzz = itemzz;

        String name = (String) itemzz.get(0);
        String qty = (String) itemzz.get(1);
        itmName.setText(name);
        itmQty.setText(qty);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
}
