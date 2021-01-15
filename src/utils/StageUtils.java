package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageUtils {
    public static Stage stage;

    public static void openView(String view, Boolean... reload) {
        try {
            Parent root = FXMLLoader.load(StageUtils.class.getResource("../view/" + view));
            if (reload.length > 0) {
                stage.close();
                stage = new Stage();
                stage.setTitle("Employee manager");
            }
            stage.setScene(new Scene(root, 1319, 861));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
