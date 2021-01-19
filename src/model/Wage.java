package model;

import java.io.Serializable;

public class Wage implements Serializable {
    private String id;
    private String name;
    private String month;
    private String wage;
    private String dayOff;

    public String getDayOff() {
        return dayOff;
    }

    public void setDayOff(String dayOff) {
        this.dayOff = dayOff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Wage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", month='" + month + '\'' +
                ", wage='" + wage + '\'' +
                ", dayOff='" + dayOff + '\'' +
                '}';
    }
}
