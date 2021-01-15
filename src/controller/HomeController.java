package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.StageUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> clName;

    @FXML
    private TableColumn<Employee, String> clId;

    @FXML
    private TableColumn<Employee, String> clGender;

    @FXML
    private TableColumn<Employee, String> clPhoneNumber;

    @FXML
    private TableColumn<Employee, String> clAddress;

    @FXML
    private TableColumn<Employee, String> clEmail;

    @FXML
    private TableColumn<Employee, String> clDateOfBirth;

    @FXML
    private TableColumn<Employee, String> clDayBeginWork;

    private ObservableList<Employee> employeeList;

    @FXML
    private TextField txtDateOfBirth;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDayBegin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeList = FXCollections.observableArrayList();
        writeFileToList();
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        clAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        clDayBeginWork.setCellValueFactory(new PropertyValueFactory<>("dayBeginWork"));
        tableView.setItems(employeeList);
    }

    public void addEmployee() {
        Employee e = new Employee();
        e.setId(txtId.getText());
        e.setName(txtName.getText());
        e.setGender(txtGender.getText());
        e.setPhoneNumber(txtPhoneNumber.getText());
        e.setAddress(txtAddress.getText());
        e.setEmail(txtEmail.getText());
        e.setDateOfBirth(txtDateOfBirth.getText());
        e.setDayBeginWork(txtDayBegin.getText());
        employeeList.add(e);
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("src/file.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<>(employeeList));
            oos.close();
            fos.close();
            System.out.println("File save successly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFileToList() {
        try {
            FileInputStream fis = new FileInputStream("src/file.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ObservableList<Employee> employees = FXCollections.observableList((List<Employee>) ois.readObject());
            employeeList.addAll(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent event) {
        Employee selected = tableView.getSelectionModel().getSelectedItem();
        employeeList.remove(selected);
    }

    public void findPerson() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        for (int i = 0; i < employeeList.size(); i++) {
            if (txtId.getText().equals(clId.getCellData(i)) || txtName.getText().equals(clName.getCellData(i)) || txtPhoneNumber.getText().equals(clPhoneNumber.getCellData(i)) || txtGender.getText().equals(clGender.getCellData(i))) {
                list.add(employeeList.get(i));
            }
        }
        tableView.setItems(list);
    }

    public void goBack() {
        StageUtils.openView("../view/home.fxml", true);
    }

//    public void change(TableColumn<Employee, String> column){
//        tableView.setEditable(true);
//        column.setCellFactory(TextFieldTableCell.forTableColumn());
//        column.setOnEditCommit((TableColumn.CellEditEvent<Employee, String> event) -> {
//            TablePosition<Employee, String> pos = event.getTablePosition();
//            String newId = event.getNewValue();
//            int row = pos.getRow();
//            // m phải lấy đc field từ đây
//            // xem nó sửa filed nào thì set value vào field đấy
////            int a = pos.getColumn();
//            Employee person = event.getTableView().getItems().get(row);
//            person.setId(newId);
//        });
//    }


    public void edit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/edit.fxml"));
        Parent editView = loader.load();
        Scene scene = new Scene(editView);
        EditController ctrl = loader.getController();
        Employee e = tableView.getSelectionModel().getSelectedItem();
        ctrl.setEmployee(e);
        stage.setScene(scene);
    }

    public void wage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/wage.fxml"));
        Parent editView = loader.load();
        Scene scene = new Scene(editView);
        WageController ctrl = loader.getController();
        Employee e = tableView.getSelectionModel().getSelectedItem();
        ctrl.setEmployee(e);
        stage.setScene(scene);
    }

}
