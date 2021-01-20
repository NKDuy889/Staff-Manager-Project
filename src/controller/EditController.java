package controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Employee;
import model.Wage;
import utils.StageUtils;
import utils.Storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class EditController extends BaseController {
    @FXML
    TextField idTxt;

    @FXML
    TextField nameTxt;

    @FXML
    TextField sdtTxt;

    @FXML
    TextField arTxt;

    @FXML
    TextField emailTxt;

    @FXML
    TextField nsTxt;

    @FXML
    TextField dbgTxt;

    @FXML
    RadioButton male;

    @FXML
    RadioButton female;

    private ToggleGroup toggleGroup;

    private boolean isEdit = true;

    private boolean isDate = true;

    private boolean idExist = true;

    @Override
    public void setValueDialog(Object value) {
        Employee employee = (Employee) value;
        idTxt.setText(employee.getId());
        if (idTxt.getText() == null) {
            isEdit = false;
        } else {
            isEdit = true;
            idTxt.setDisable(true);
        }
        nameTxt.setText(employee.getName());
        if ("Nam".equals(employee.getGender())) {
            male.setSelected(true);
        } else if ("Nữ".equals(employee.getGender())) {
            female.setSelected(true);
        }
        sdtTxt.setText(employee.getPhoneNumber());
        arTxt.setText(employee.getAddress());
        emailTxt.setText(employee.getEmail());
        nsTxt.setText(employee.getDateOfBirth());
        dbgTxt.setText(employee.getDayBeginWork());
        male.setUserData("Nam");
        female.setUserData("Nữ");
        toggleGroup = new ToggleGroup();
        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);
    }

    public void goBack() {
        StageUtils.close();
    }

    public void edit() {
        Employee employee;
        if (isEdit) {
            employee = Storage.getById(idTxt.getText());
        } else {
            employee = new Employee();
            employee.setId(idTxt.getText());
        }
        employee.setName(nameTxt.getText());
        employee.setGender((String) toggleGroup.getSelectedToggle().getUserData());
        employee.setPhoneNumber(sdtTxt.getText());
        employee.setAddress(arTxt.getText());
        employee.setEmail(emailTxt.getText());
        employee.setDateOfBirth(nsTxt.getText());
        employee.setDayBeginWork(dbgTxt.getText());
        checkIsEdit(employee);
        goBack();
    }

    public void checkIsEdit(Employee employee) {
            if (isEdit) {
                Storage.save();
            } else {
                Storage.add(employee);
            }
    }

    public static void read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/wage.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Wage> e = (List<Wage>) ois.readObject();
        System.out.println(e);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EditController.read();
    }
}
