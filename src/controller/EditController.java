package controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Employee;
import utils.StageUtils;
import utils.Storage;

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

    @Override
    public void setValueDialog(Object value) {
        Employee employee = (Employee) value;
        if (employee.getId() == null) {
            
        }
        idTxt.setText(employee.getId());
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
        Employee employee = Storage.getById(idTxt.getText());
        employee.setName(nameTxt.getText());
        employee.setGender((String) toggleGroup.getSelectedToggle().getUserData());
        employee.setPhoneNumber(sdtTxt.getText());
        employee.setAddress(arTxt.getText());
        employee.setEmail(emailTxt.getText());
        employee.setDateOfBirth(nsTxt.getText());
        employee.setDayBeginWork(dbgTxt.getText());
        Storage.save();
        goBack();
    }
}
