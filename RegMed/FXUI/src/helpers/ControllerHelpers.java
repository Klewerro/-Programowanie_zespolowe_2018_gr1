package helpers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ControllerHelpers{

    public void SwitchScene(String childView, ActionEvent event) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("/views/" + childView + ".fxml"));
        Scene childScene = new Scene(child);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(childScene);
        window.show();
    }

    public AnchorPane SwitchAnchor(String childView, ActionEvent event) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("/views/" + childView + ".fxml"));
        AnchorPane anchor = new AnchorPane(child);

        return anchor;
    }

}
