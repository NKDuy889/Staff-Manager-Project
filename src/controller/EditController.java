package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Employee;
import utils.StageUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EditController {
    @FXML
    TextField idTxt;

    @FXML
    TextField nameTxt;

    @FXML
    TextField genderTxt;

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

    private ObservableList<Employee> list = FXCollections.observableArrayList();

    public void setEmployee(Employee employee){
        writeFileToList();
        idTxt.setText(employee.getId());
        nameTxt.setText(employee.getName());
        genderTxt.setText(employee.getGender());
        sdtTxt.setText(employee.getPhoneNumber());
        arTxt.setText(employee.getAddress());
        emailTxt.setText(employee.getEmail());
        nsTxt.setText(employee.getDateOfBirth());
        dbgTxt.setText(employee.getDayBeginWork());
    }

    public void goBack() {
        StageUtils.openView("../view/home.fxml", true);
    }

    public void writeFileToList() {
        try {
            FileInputStream fis = new FileInputStream("src/file.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ObservableList<Employee> employees = FXCollections.observableList((List<Employee>) ois.readObject());
            list.addAll(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void edit() {
        for (int i = 0; i < list.size(); i++) {
            if (idTxt.getText().equals(list.get(i).getId())){
                list.remove(i);
            }
        }
        add();
    }

    public void add(){
        Employee employee = new Employee();
        employee.setId(idTxt.getText());
        employee.setName(nameTxt.getText());
        employee.setGender(genderTxt.getText());
        employee.setPhoneNumber(sdtTxt.getText());
        employee.setAddress(arTxt.getText());
        employee.setEmail(emailTxt.getText());
        employee.setDateOfBirth(nsTxt.getText());
        employee.setDayBeginWork(dbgTxt.getText());
        list.add(employee);
        writeListToFile();
    }

    public void writeListToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("src/file.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<>(list));
            oos.close();
            fos.close();
            System.out.println("File save successly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            FileInputStream fis = new FileInputStream("src/file.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ObservableList<Employee> employees = FXCollections.observableList((List<Employee>) ois.readObject());
            System.out.println(employees);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
