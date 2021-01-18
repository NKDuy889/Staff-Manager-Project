package utils;

import controller.BaseController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;
import java.util.stream.Collectors;

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
        baseController = null;
    }

    public static <T extends BaseController> void addButtonToTable(TableView tableView, T clzz) {
        ObservableList<TableColumn> lstColumn = tableView.getColumns();
        List<TableColumn> array = lstColumn.stream().filter(column -> "action".equals(column.getId())).collect(Collectors.toList());
        if(array.isEmpty()) {
            return;
        }
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(final TableColumn param) {
                final TableCell cell = new TableCell<Object, Void>() {
                    private Button btnEdit = new Button("Edit");
                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            Object obj = getTableView().getItems().get(getIndex());
                            clzz.onEdit(obj);
                        });
                        btnEdit.setPrefWidth(60);
                        btnEdit.setLayoutX(20);
                        btnEdit.setCursor(Cursor.HAND);
                    }
                    private final Button btnDelete = new Button("Delete");
                    {
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Object obj = getTableView().getItems().get(getIndex());
                            clzz.onDelete(obj);
                        });
                        btnDelete.setPrefWidth(90);
                        btnDelete.setLayoutX(100);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Pane pane = new Pane();
                            pane.getChildren().add(btnEdit);
                            pane.getChildren().add(btnDelete);
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };
        array.get(0).setCellFactory(cellFactory);
    }

}