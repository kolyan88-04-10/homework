package com.alevel.prokopchuk.hw23.beans;

import com.alevel.prokopchuk.hw23.entities.Entity;
import java.sql.Date;

public class EmployeeBean extends Entity {
    private String firstName;
    private String lastName;
    private int currentSalary;
    private int totalSalary;
    private String titleName;
    private Date hireDate;
    private String departmentName;

    public EmployeeBean(int id, String firstName, String lastName,
                        String titleName, int totalSalary, Date hireDate) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleName = titleName;
        this.hireDate = hireDate;
        this.totalSalary = totalSalary;
    }

    public EmployeeBean(int id, String firstName, String lastName, String titleName,
                        int currentSalary, int totalSalary ) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentSalary = currentSalary;
        this.totalSalary = totalSalary;
        this.titleName = titleName;
    }

    public EmployeeBean(int id, String firstName, String lastName, Date hireDate, String departmentName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.departmentName = departmentName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(int currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "id='" + getId() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                titleName != null ? (", titleName='" + titleName + '\'') : "" +
                departmentName != null ? (", departmentName='" + departmentName + '\'') : "" +
                currentSalary != null ? (", currentSalary=" + currentSalary) : "" +
                totalSalary != null ? (", totalSalary=" + totalSalary ) : "" +
                hireDate != null ? (", hireDate=" + hireDate) : "" +
                '}';
    }
}
