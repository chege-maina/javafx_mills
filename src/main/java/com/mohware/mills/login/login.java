package com.mohware.mills.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class login implements Initializable, LoginView {

    @FXML
    private StackPane rootpanes;
    @FXML
    private ImageView logo_img;
    @FXML
    private Label close_login, emailError, passError;
    @FXML
    private Button btn_login;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField email;
    LoginPresenter presenter;
    String checkers = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showimages();
        presenter = new LoginPresenter(this);

        close_login.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        btn_login.setOnMouseClicked(mouseEvent -> {
            if (!ValEmail() | !ValPass()) {
                return;
            }
            String mail = email.getText();
            String pass1 = pass.getText();

            presenter.Login(mail, pass1);

        });
    }

    public void showimages() {
        File logox = new File("images/php.png");
        Image logo_image = new Image(logox.toURI().toString());
        logo_img.setImage(logo_image);
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

    private boolean ValEmail() {
        String val = email.getText().toString();
        if (val.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Email cannot be left empty!!", "WARNING", 0);
            String message = "Email cannot be left empty!!";
            emailError.setText(message);
            email.getStyleClass().add("textInputError");
            return false;
        } else {
            emailError.setText("");
            email.getStyleClass().add("textInput");
            return true;
        }
    }

    private boolean ValPass() {
        String val = pass.getText().toString();
        if (val.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Password cannot be left empty!!", "WARNING", 0);
            String message = "Password cannot be left empty!!";
            passError.setText(message);
            pass.getStyleClass().add("textInputError");
            return false;
        } else {
            passError.setText("");
            pass.getStyleClass().add("textInput");
            return true;
        }
    }

    @Override
    public void onAddSuccess(String message, String fname, String lname, String designation, String status) {
        Platform.runLater(() -> {
            Parent root;
            try {
                if (designation.equals("ADMIN")) {
                    root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
                    Stage stage = new Stage();
                    //stage.setMaximized(true);
                    stage.setScene(new Scene(root, 700, 700));
                    stage.setMaximized(true);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                }
                else if (designation.equals("CASHIER")) {
                    root = FXMLLoader.load(getClass().getResource("/fxml/pos.fxml"));
                    Stage stage = new Stage();
                    //stage.setMaximized(true);
                    stage.setScene(new Scene(root, 700, 700));
                    stage.setMaximized(true);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                }
                //Stage stage = new Stage();
                //stage.initStyle(StageStyle.UNDECORATED);
                //stage.setScene(new Scene(root, 861, 512));
                //stage.setMaximized(true);
                //stage.setScene(new Scene(root, 861, 512));
                //stage.show();
                btn_login.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onAddError(String message) {
        //System.out.println(message);
        Platform.runLater(() -> {
            infodialog("Okay", message);
        });

    }

}
