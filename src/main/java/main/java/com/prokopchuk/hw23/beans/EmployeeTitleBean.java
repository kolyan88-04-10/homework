package main.java.com.prokopchuk.hw23.beans;

import main.java.com.prokopchuk.hw23.entities.Entity;
import java.sql.Date;

public class EmployeeTitleBean extends Entity {
    private String firstName;
    private String lastName;
    private int totalSalary;
    private String titleName;
    private Date hireDate;

    public EmployeeTitleBean(int id, String firstName, String lastName,
                             String titleName, int totalSalary, Date hireDate) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleName = titleName;
        this.hireDate = hireDate;
        this.totalSalary = totalSalary;
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

    @Override
    public String toString() {
        return "EmployeeTitleBean{" +
                "id='" + getId() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalSalary=" + totalSalary +
                ", titleName='" + titleName + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
