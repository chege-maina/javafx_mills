package com.mohware.mills.pos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mohware.mills.login.login;
import com.mohware.mills.main.main;
import com.mohware.mills.pos.ReceiptItemsController;
import com.mohware.mills.model.CustHelp;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

public class PosController implements PosView, Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox receipt;

    @FXML
    private Button cancel, save, chkoutCancelBtn, chkoutPay;

    @FXML
    private AnchorPane acPos, acCheckout;

    @FXML
    private ImageView action1, action2, cat1, logo, search;

    @FXML
    private GridPane grid;

    @FXML
    private Label TotalPrice, dateLabel, timeLabel, userLabel, TaxAmount, SubTotal, LblTotal, changeLbl;

    @FXML
    private TextField mpesaTxt, transcodeTxt, cashTxt;

    @FXML
    private StackPane rootpanes;
    List<CustHelp> item;
    private mylistener listenerz;
    PosPresenter presenter;
    ArrayList<ArrayList<String>> receiptlist1;
    @FXML
    private ProgressIndicator p_Indicator;
    String TotalAmount;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showimages();
        userLabel.setText(login.USER);
        addDate();
        addTime();
        textFieldInit();
        hideItems();
        initialView();
        p_Indicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        presenter = new PosPresenter(this);
        presenter.getItems();
        receiptlist1 = new ArrayList<>();

        cancel.setOnMouseClicked(mouseEvent -> {
            clearme();

        });
        chkoutPay.setOnMouseClicked(mouseEvent -> {
            String source = System.getProperty("user.dir") + "/receipt.jrxml";
            if (new File(source).exists() == false) {
                infodialog("Okay", "SPECIFY THE REPORT ROOT!!");
                return;
            }
            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(source);
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("recno", "KELMO-1");
                param.put("date", dateLabel.getText());
                param.put("time", timeLabel.getText());

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JREmptyDataSource());
                JasperPrintManager.printReport(jasperPrint, false);
            } catch (JRException ex) {
                Logger.getLogger(PosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        chkoutCancelBtn.setOnMouseClicked(mouseEvent -> {
            hideItems();
            acPos.setVisible(true);
            mpesaTxt.setText("");
            cashTxt.setText("");
            changeLbl.setText("");
            transcodeTxt.setText("");
            LblTotal.setText("");

        });
        save.setOnMouseClicked(mouseEvent -> {
            //hideItems();
            if (TotalPrice.getText().equals(main.CURRENCY + "0.00/=")) {
                infodialog("Okay", "No Items on the Receipt");
            } else {
                acCheckout.setVisible(true);
                LblTotal.setText(TotalAmount);
            }

        });

        Platform.runLater(() -> {

            listenerz = new mylistener() {
                @Override
                public void onClickListener(CustHelp recitem) {
                    getRecItems(recitem);

                }

                @Override
                public void onClickListener3(String prod, String qtymm) {
                    qtyTyped(prod, qtymm);

                }

            };

        });

    }

    public void textFieldInit() {
        mpesaTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    mpesaTxt.setText(oldValue);
                } else if (newValue.equals("")) {
                    newValue = "0.0";
                    Double entered = Double.parseDouble(newValue);
                    Double total = Double.parseDouble(TotalAmount);
                    if (entered > total) {
                        mpesaTxt.setText(oldValue);
                        infodialog("Okay", "MPESA Payments Must Equal Total or Less");

                    }
                } else if (!cashTxt.getText().isEmpty()) {
                    Double entered = Double.parseDouble(newValue);
                    Double cash = Double.parseDouble(cashTxt.getText());
                    Double total = Double.parseDouble(TotalAmount);
                    Double check = cash + entered;
                    if (cash > total || cash == total) {
                        mpesaTxt.setText(oldValue);
                    } else if (entered > check) {
                        mpesaTxt.setText(oldValue);
                        infodialog("Okay", "MPESA Payments Must Equal Total or Less");

                    }
                } else if (Double.parseDouble(newValue) > Double.parseDouble(TotalAmount)) {
                    mpesaTxt.setText(oldValue);
                    infodialog("Okay", "MPESA Payments Must Equal Total or Less");
                }
            }
        }
        );
        cashTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    cashTxt.setText(oldValue);
                } else if (newValue.equals("")) {
                    newValue = "0.0";
                    Double entered = Double.parseDouble(newValue);
                    Double total = Double.parseDouble(TotalAmount);
                    Double change = entered - total;
                    changeLbl.setText("" + change);
                }

                Double entered = Double.parseDouble(newValue);
                Double total = Double.parseDouble(TotalAmount);
                Double change = entered - total;
                changeLbl.setText("" + change);
            }
        }
        );

    }

    public void hideItems() {
        acPos.setVisible(false);
        acCheckout.setVisible(false);

    }

    public void initialView() {
        acPos.setVisible(true);

    }

    public void qtyTyped(String name, String qty) {

        //String name = prod.get(0).toString();
        int totsize = receiptlist1.size();
        Double ttlamt = 0.0;
        int total;

        for (int i = 0; i < totsize; i++) {
            String namechk = receiptlist1.get(i).get(0);
            if (namechk.equals(name)) {
                String getStock = receiptlist1.get(i).get(4);
                String price = receiptlist1.get(i).get(2);
                int QTY = Integer.parseInt(qty);
                int stockchk = Integer.parseInt(getStock);
                if (QTY > stockchk) {
                    infodialog("Okay", "Quantity Cannot Exceed Stock");
                } else {
                    total = QTY * Integer.parseInt(price);

                    receiptlist1.get(i).set(1, "" + QTY);
                    receiptlist1.get(i).set(3, "" + total);

                    receipt.getChildren().clear();

                    totsize = receiptlist1.size();
                    for (int j = 0; j < totsize; j++) {
                        ttlamt = ttlamt + Double.parseDouble(receiptlist1.get(j).get(3).replace(",", ""));

                        TotalPrice.setText(main.CURRENCY + ttlamt + "/=");
                    }
                    for (int j = 0; j < totsize; j++) {
                        FXMLLoader fxmlloader = new FXMLLoader();
                        fxmlloader.setLocation(getClass().getResource("/fxml/receiptItems.fxml"));

                        try {

                            HBox hBox = fxmlloader.load();
                            ReceiptItemsController itemscontroller = fxmlloader.getController();
                            itemscontroller.receiptItems(receiptlist1.get(j), listenerz);
                            receipt.getChildren().add(hBox);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

        }

    }

    public void infodialog(String btnText, String msg) {
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

    public void getRecItems(CustHelp recitem) {

        int total;
        int on = 0;
        int qty_entered = 1;
        Double ttlamt = 0.0;
        Double tax_amt = 0.0;
        Double tax_amount = 0.0;
        Double sub_total = 0.0;
        receipt.getChildren().clear();
        int totsize = receiptlist1.size();
        String price = recitem.getPrice();
        String stock = recitem.getCheckqty();
        String item = recitem.getItem();
        String code = recitem.getCode();
        String taxpc = recitem.getTax();
        String unit = recitem.getSelling_unit();
        String conversion = recitem.getConversion();

        if (totsize > 0) {
            for (int i = 0; i < totsize; i++) {
                String namechk = receiptlist1.get(i).get(0);
                if (namechk.equals(item)) {
                    String getQTY = receiptlist1.get(i).get(1);
                    int QTY = qty_entered + Integer.parseInt(getQTY);
                    int stockchk = Integer.parseInt(stock);
                    on = 1;
                    if (QTY > stockchk) {
                        infodialog("Okay", "Quantity Cannot Exceed Stock");
                    } else {
                        total = QTY * Integer.parseInt(price);
                        tax_amt = (Double.parseDouble("" + total) * Double.parseDouble(taxpc)) / (100 + Double.parseDouble(taxpc));
                        receiptlist1.get(i).set(1, "" + QTY);
                        receiptlist1.get(i).set(3, "" + total);
                        receiptlist1.get(i).set(8, "" + tax_amt);
                    }

                }

            }
            if (on == 0) {
                int QTY = qty_entered;
                int stockchk = Integer.parseInt(stock);
                if (QTY > stockchk) {
                    infodialog("Okay", "Quantity Cannot Exceed Stock");
                } else {
                    total = QTY * Integer.parseInt(price);
                    tax_amt = (Double.parseDouble("" + total) * Double.parseDouble(taxpc)) / (100 + Double.parseDouble(taxpc));
                    ArrayList<String> listelm = new ArrayList<>();
                    listelm.add(item);
                    listelm.add("" + qty_entered);
                    listelm.add(price);
                    listelm.add("" + total);
                    listelm.add(code);
                    listelm.add(taxpc);
                    listelm.add(unit);
                    listelm.add(conversion);
                    listelm.add("" + tax_amt);
                    receiptlist1.add(listelm);
                }
            }
        } else {
            int QTY = qty_entered;
            int stockchk = Integer.parseInt(stock);
            if (QTY > stockchk) {
                infodialog("Okay", "Quantity Cannot Exceed Stock");
            } else {
                total = QTY * Integer.parseInt(price);
                tax_amt = (Double.parseDouble("" + total) * Double.parseDouble(taxpc)) / (100 + Double.parseDouble(taxpc));
                ArrayList<String> listelm = new ArrayList<>();
                listelm.add(item);
                listelm.add("" + qty_entered);
                listelm.add(price);
                listelm.add("" + total);
                listelm.add(code);
                listelm.add(taxpc);
                listelm.add(unit);
                listelm.add(conversion);
                listelm.add("" + tax_amt);
                receiptlist1.add(listelm);
            }
        }
        totsize = receiptlist1.size();
        for (int i = 0; i < totsize; i++) {
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            ttlamt = ttlamt + Double.parseDouble(receiptlist1.get(i).get(3).replace(",", ""));
            tax_amount = tax_amount + Double.parseDouble(receiptlist1.get(i).get(8).replace(",", ""));
            sub_total = ttlamt - tax_amount;
            String totalfrmt = formatter.format(ttlamt);
            String taxfrmt = formatter.format(tax_amount);
            String subttlfrmt = formatter.format(sub_total);

            TotalPrice.setText(main.CURRENCY + totalfrmt + "/=");
            //String jaba = TotalPrice.getText().replace(main.CURRENCY, "");
            SubTotal.setText(main.CURRENCY + subttlfrmt + "/=");
            TaxAmount.setText(main.CURRENCY + taxfrmt + "/=");
            TotalAmount = "" + ttlamt;
        }
        for (int i = 0; i < totsize; i++) {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/fxml/receiptItems.fxml"));

            try {

                HBox hBox = fxmlloader.load();
                ReceiptItemsController itemscontroller = fxmlloader.getController();
                itemscontroller.receiptItems(receiptlist1.get(i), listenerz);
                receipt.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void showimages() {

        File actionx2 = new File("images/search.png");
        Image logo_image = new Image(actionx2.toURI().toString());
        search.setImage(logo_image);
    }

    public void addDate() {
        String txtdate = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
        dateLabel.setText(txtdate);
    }

    public void addTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String saa = sdf.format(new Date());
        timeLabel.setText(saa);
    }

    private void clearme() {
        receiptlist1.clear();
        receipt.getChildren().clear();
        TotalPrice.setText(main.CURRENCY + "0.00/=");
        TaxAmount.setText(main.CURRENCY + "0.00/=");
        SubTotal.setText(main.CURRENCY + "0.00/=");
    }

    public void saveUrl(final String filename, String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            if (urlString.contains(" ")) {
                urlString = urlString.replace(" ", "%20");
            }
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }

    @Override
    public void onGetResult(List<CustHelp> items) {
        item = items;

        int x = items.size();
        Platform.runLater(() -> {

            int column = 0;
            int row = 1;

            try {

                for (int i = 0; i < x; i++) {
                    String ditem = items.get(i).getItem();
                    String dpath = items.get(i).getPath();
                    String dprice = items.get(i).getPrice();
                    String dqty = items.get(i).getCheckqty();

                    File f = new File("images/" + dpath);
                    if (!f.exists()) {
                        String logox = "images/" + dpath;
                        //String getuurl = "http://192.168.0.251:80/kelmo/" + dpath;
                        String getuurl = "http://www.severinombae.net/kelmo/" + dpath;
                        saveUrl(logox, getuurl);
                    }

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("/fxml/Items.fxml"));
                    AnchorPane ac = fxmlloader.load();
                    ItemsController itemscontroller = fxmlloader.getController();
                    itemscontroller.setItems(ditem, dpath, dprice, dqty, items.get(i), listenerz);
//get the array from the other array.... it is marked man...
                    if (column == 4) {
                        column = 0;
                        row++;
                    }

                    grid.add(ac, column++, row);
                    GridPane.setMargin(ac, new Insets(10));

                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
            p_Indicator.setVisible(false);
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Platform.runLater(() -> {
            infodialog("Okay", message);
        });
    }

    @Override
    public void showLoading() {
        Platform.runLater(() -> {
            p_Indicator.setVisible(true);
        });
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideLoading() {
        Platform.runLater(() -> {
            //p_Indicator.setVisible(false);
        });
    }

    @Override
    public void hideProgress() {
    }

}
