module com.mohware.mills {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires io.github.classgraph;
    requires retrofit2;

    opens com.mohware.mills.main to javafx.fxml;
    //opens java.base/java.lang.invoke = retrofit2;
    opens com.mohware.mills.login to javafx.fxml;
    //opens com.mohware.mills.login to retrofit2;
    opens com.mohware.mills.dashboard to javafx.fxml;
    opens com.mohware.mills.pos to javafx.fxml;
    exports com.mohware.mills.main;
    requires retrofit2.converter.gson;
    requires com.google.gson;
    requires java.base;
    //requires picasso;
    requires org.json;
    
    
}
