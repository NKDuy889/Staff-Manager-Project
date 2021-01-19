package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WageController {
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
    private TextField sumWageTf;

    @FXML
    private TableView table;

    public void setEmployee(Employee employee) {
        idTf.setText(employee.getId());
        nameTf.setText(employee.getName());
        dayBeginWork.setText(employee.getDayBeginWork());
    }

    public Date getDate() throws ParseException {
        String d = dayBeginWork.getText();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        return date;
    }


}
