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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author mohware
 */
public class dispatchList implements Initializable{
    
     @FXML
    private Label displistRecno, displistCustomer, displistDate;

    @FXML
    private Button disAction;
    
    @FXML
    public void click(MouseEvent mouseEvent){
        listenerz.onClickListener4(itemzz);
    }
    private mylistener listenerz;
    ArrayList itemzz;

    
    public void DispatchList(ArrayList itemzz, mylistener listenerz) {
        this.listenerz = listenerz;
        this.itemzz = itemzz;

        String recno = (String) itemzz.get(0);
        String customer = (String) itemzz.get(1);
        String date = (String) itemzz.get(2);
        displistRecno.setText(recno);
        displistCustomer.setText(customer);
        displistDate.setText(date);

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
}
