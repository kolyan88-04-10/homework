package com.alevel.prokopchuk.hw24;

import com.alevel.prokopchuk.hw24.models.Gender;
import com.alevel.prokopchuk.hw24.services.EmployeeService;
import com.alevel.prokopchuk.hw24.models.Employee;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(10001);
        System.out.println(employee);
        Employee newEmployee = new Employee();
//        newEmployee.setEmpNo(1);
        newEmployee.setFirstName("Nikolay");
        newEmployee.setLastName("Prokopchuk");
        newEmployee.setBirthDate(Date.valueOf("1988-04-10"));
        newEmployee.setGender(Gender.M);
        newEmployee.setHireDate(Date.valueOf("2016-06-10"));
        employeeService.saveEmployee(newEmployee);
//        Employee employee = employeeService.findEmployee(1);
//        System.out.println(employee);
//        employeeService.deleteEmployee(employee);
    }
}
