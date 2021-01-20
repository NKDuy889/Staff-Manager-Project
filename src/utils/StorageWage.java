package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Wage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StorageWage {
    private static List<Wage> listWage;

    public static List<Wage> getListWage() {
        if (listWage == null) {
            setListWage(readFileWage());
        }
        return listWage;
    }

    public static void setListWage(List<Wage> listWage) {
        StorageWage.listWage = listWage;
    }

    public static void add(Wage w, Object... table) {
        listWage.add(w);
        saveWage();
        if (table.length > 0) {
            ((TableView<Wage>) table[0]).setItems(observerList());
        }
    }

    public static ObservableList<Wage> observerList() {
        return FXCollections.observableArrayList(getListWage());
    }

    public static void delete(Wage e, Object... table) {
        listWage.remove(e);
        saveWage();
        if (table.length > 0) {
            ((TableView<Wage>) table[0]).setItems(observerList());
        }
    }

    public static void saveWage() {
        try {
            FileOutputStream fos = new FileOutputStream("src/wage.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listWage);
            oos.close();
            fos.close();
            System.out.println("File save successly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Wage> readFileWage() {
        try {
            FileInputStream fis = new FileInputStream("src/wage.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Wage>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
