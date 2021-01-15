package controller;

import utils.StageUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnExit;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassWord;

    public void login(ActionEvent actionEvent) {
        try {
            btnLogin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (txtUserName.getText().equals("admin") && txtPassWord.getText().equals("admin")) {
                        StageUtils.openView("../view/home.fxml", true);
                    } else {
                        Alert btnLoginWrong = new Alert(Alert.AlertType.INFORMATION);
                        btnLoginWrong.setContentText("INVALID USER NAME OR PASS WORD. TRY AGAIN");
                        btnLoginWrong.show();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
