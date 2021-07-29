/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohware.mills.pos;

import com.mohware.mills.model.RecModel;
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
public class saleListController implements Initializable {

    @FXML
    private Label receiptNo, dateSale, timeSale, mpesa, cash, total, code;

    public void saleList(RecModel itemzz) {

        receiptNo.setText(itemzz.getRec_no());
        dateSale.setText(itemzz.getRec_date());
        timeSale.setText(itemzz.getRec_time());
        mpesa.setText(itemzz.getRec_mpesa());
        cash.setText(itemzz.getRec_cash());
        total.setText(itemzz.getRec_Atotal());
        code.setText(itemzz.getRec_transcode());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
