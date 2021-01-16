package utils;

import controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StageUtils {
    public static Stage stage;
    public static Stage stageModal;
    private static BaseController baseController;

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

    public static void openDialog(String view, String title, Object value, Object... obj) {
        try {
            if (obj.length > 0)
                baseController = (BaseController) obj[0];
            if (stageModal == null) {
                stageModal = new Stage();
                stageModal.setTitle(title);
                stageModal.initModality(Modality.APPLICATION_MODAL);
                if (obj.length > 0)
                    stageModal.setOnCloseRequest(event -> baseController.closeEvent());
            }
            FXMLLoader fxml = new FXMLLoader(StageUtils.class.getResource("../view/" + view));
            Parent root = fxml.load();
            fxml.setRoot(root);
            Scene scene = new Scene(fxml.getRoot());
            stageModal.setScene(scene);
            ((BaseController) fxml.getController()).setValueDialog(value);
            stageModal.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        if (baseController != null)
            baseController.closeEvent();
        stageModal.close();
    }

}