package controller;

import utils.StageUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/home.fxml"));
        primaryStage.setTitle("Rabbit company");
        primaryStage.setScene(new Scene(root, 1319, 861));
        primaryStage.show();
        StageUtils.stage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
        EditController h = new EditController();
        h.read();
    }
}
