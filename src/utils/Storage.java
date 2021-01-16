package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<Employee> listEmployees;

    public static List<Employee> getListEmployees() {
        if (listEmployees == null) {
            setListEmployees(readFileToList());
        }
        return listEmployees;
    }

    public static void add(Employee e, Object... table) {
        listEmployees.add(e);
        save();
        if (table.length > 0) {
            ((TableView<Employee>) table[0]).setItems(observerList());
        }
    }

    public static Employee getById(String id){
        for (Employee e : listEmployees){
            if (id.equals(e.getId())){
                return e;
            }
        }
        return null;
    }

    public static void delete(Employee e, Object... table) {
        listEmployees.remove(e);
        save();
        if (table.length > 0) {
            ((TableView<Employee>) table[0]).setItems(observerList());
        }
    }

    public static ObservableList<Employee> observerList() {
        return FXCollections.observableArrayList(getListEmployees());
    }

    public static void setListEmployees(List<Employee> listEmployees) {
        Storage.listEmployees = listEmployees;
    }

    public static List<Employee> readFileToList() {
        try {
            FileInputStream fis = new FileInputStream("src/file.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Employee>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public static void save() {
        try {
            FileOutputStream fos = new FileOutputStream("src/file.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listEmployees);
            oos.close();
            fos.close();
            System.out.println("File save successly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
