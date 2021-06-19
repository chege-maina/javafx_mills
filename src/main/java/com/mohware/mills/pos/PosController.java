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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PosController implements PosView, Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox receipt;

    @FXML
    private Button cancel;

    @FXML
    private ImageView action1, action2, cat1, logo, search;

    @FXML
    private GridPane grid;

    @FXML
    private Label TotalPrice, dateLabel, timeLabel, userLabel;

    @FXML
    private StackPane rootpanes;
    List<CustHelp> item;
    private mylistener listenerz;
    PosPresenter presenter;
    ArrayList<ArrayList<String>> receiptlist1;
    @FXML
    private ProgressIndicator p_Indicator;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showimages();
        userLabel.setText(login.USER);
        addDate();
        addTime();
        p_Indicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        presenter = new PosPresenter(this);
        presenter.getItems();
        receiptlist1 = new ArrayList<>();

        cancel.setOnMouseClicked(mouseEvent -> {
            clearme();

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
        receipt.getChildren().clear();
        int totsize = receiptlist1.size();
        String price = recitem.getPrice();
        String stock = recitem.getCheckqty();
        String item = recitem.getItem();

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

                        receiptlist1.get(i).set(1, "" + QTY);
                        receiptlist1.get(i).set(3, "" + total);
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
                    ArrayList<String> listelm = new ArrayList<>();
                    listelm.add(item);
                    listelm.add("" + qty_entered);
                    listelm.add(price);
                    listelm.add("" + total);
                    listelm.add(stock);
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
                ArrayList<String> listelm = new ArrayList<>();
                listelm.add(item);
                listelm.add("" + qty_entered);
                listelm.add(price);
                listelm.add("" + total);
                listelm.add(stock);
                receiptlist1.add(listelm);
            }
        }
        totsize = receiptlist1.size();
        for (int i = 0; i < totsize; i++) {
            ttlamt = ttlamt + Double.parseDouble(receiptlist1.get(i).get(3).replace(",", ""));
            TotalPrice.setText(main.CURRENCY + ttlamt + "/=");
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
