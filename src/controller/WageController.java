package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employee;
import model.Wage;
import utils.StorageWage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class WageController implements Initializable {
    private final int wage = 200000;

    @FXML
    private TextField idTf;

    @FXML
    private TextField nameTf;

    @FXML
    private TextField dayBeginWork;

    @FXML
    private TextField monthTf;

    @FXML
    private TextField dayOffTf;

    @FXML
    private TableView table;

    @FXML
    private TextField monthS;

    @FXML
    private TextField sumW;

    @FXML
    private TableColumn<Wage, String> idCl;

    @FXML
    private TableColumn<Wage, String> nameCl;

    @FXML
    private TableColumn<Wage, String> monthCl;

    @FXML
    private TableColumn<Wage, String> wageCl;

    @FXML
    private TableColumn<Wage, String> dayOffCl;

    private boolean isOverDate = true;

    public void setEmployee(Employee employee) {
        idTf.setText(employee.getId());
        nameTf.setText(employee.getName());
        dayBeginWork.setText(employee.getDayBeginWork());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCl.setCellValueFactory(new PropertyValueFactory<>("name"));
        monthCl.setCellValueFactory(new PropertyValueFactory<>("month"));
        wageCl.setCellValueFactory(new PropertyValueFactory<>("wage"));
        dayOffCl.setCellValueFactory(new PropertyValueFactory<>("dayOff"));
        table.setItems(FXCollections.observableArrayList(StorageWage.getListWage()));
        idTf.setDisable(true);
        dayBeginWork.setDisable(true);
        nameTf.setDisable(true);
    }

    public int getDayWorking() {
        int dayOff = Integer.parseInt(dayOffTf.getText());
        String string = dayBeginWork.getText();
        String[] parts = string.split("/");
        int dayWork = Integer.parseInt(parts[0]);
        int monthWork = Integer.parseInt(parts[1]);
        int yearWork = Integer.parseInt(parts[2]);
        Calendar calendar = new GregorianCalendar(yearWork, monthWork, 0);
        int dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String string1 = monthTf.getText();
        String[] parts1 = string1.split("/");
        int month = Integer.parseInt(parts1[0]);
        int year = Integer.parseInt(parts1[1]);
        if (month < monthWork && year == yearWork) {
            isOverDate = false;
            return -1;
        } else if (year < yearWork) {
            isOverDate = false;
            return -1;
        } else if (month == monthWork && year == yearWork) {
            isOverDate = true;
            return (dayOfMonth - dayWork - 1 - dayOff) * wage;
        } else {
            isOverDate = true;
            return (dayOfMonth - dayOff) * wage;
        }
    }

    public void addList() {
        Wage wage = new Wage();
        wage.setId(idTf.getText());
        wage.setName(nameTf.getText());
        wage.setMonth(monthTf.getText());
        wage.setWage(String.valueOf(getDayWorking()));
        wage.setDayOff(dayOffTf.getText());
        if (isOverDate) {
            StorageWage.add(wage);
            table.setItems(FXCollections.observableArrayList(StorageWage.getListWage()));
        } else {
            Alert dateWrong = new Alert(Alert.AlertType.INFORMATION);
            dateWrong.setContentText("Hôm đấy bạn này chưa đi làm. Mời điền lại");
            dateWrong.show();
        }
    }

    public void deleteEvent() {
        Wage wage = (Wage) table.getSelectionModel().getSelectedItem();
        StorageWage.getListWage().remove(wage);
        StorageWage.saveWage();
        table.setItems(FXCollections.observableArrayList(StorageWage.getListWage()));
    }

    public void sumWage() {
        int b = 0;
        for (Wage w : StorageWage.getListWage()) {
            if (w.getMonth().equals(monthS.getText()) ) {
                int a = Integer.parseInt(w.getWage());
                b = b + a;
            }
            sumW.setText(String.valueOf(b));
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/home.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

}
