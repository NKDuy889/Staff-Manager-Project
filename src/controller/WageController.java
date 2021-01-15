package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Employee;

public class WageController {
    @FXML
    private TextField idTf;

    @FXML
    private TextField nameTf;

    @FXML
    private TextField dayBeginTf;

    @FXML
    private TextField monthTf;

    @FXML
    private TextField dayOffTf;

    @FXML
    private TextField sumWageTf;

    public void setEmployee(Employee employee){
        idTf.setText(employee.getId());
        nameTf.setText(employee.getName());
        dayBeginTf.setText(employee.getDayBeginWork());
    }


}
