
package com.mohware.mills.dashboard;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class picController implements Initializable{
    @FXML
    private ImageView Exit, picHolder;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showimages();
        Exit.setOnMouseClicked(mouseEvent -> {
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();

        });
    }
    public void setPic(String path) {
        File logox = new File(path);
        Image logo_image = new Image(logox.toURI().toString());
        picHolder.setImage(logo_image);


    }
    
    public void showimages() {

        File actionx2 = new File("images/x-circle-fill.png");
        Image logo_image = new Image(actionx2.toURI().toString());
        Exit.setImage(logo_image);
    }
    
}
