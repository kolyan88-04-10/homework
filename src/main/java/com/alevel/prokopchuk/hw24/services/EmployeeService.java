package com.alevel.prokopchuk.hw24.services;

import com.alevel.prokopchuk.hw24.dao.EmployeeDAOImpl;
import com.alevel.prokopchuk.hw24.dao.EmployeeDAO;
import com.alevel.prokopchuk.hw24.models.Employee;

public class EmployeeService {
    private EmployeeDAO employeeDao = new EmployeeDAOImpl();

    public EmployeeService() {
    }

    public Employee findEmployee(int id) {
        return employeeDao.findById(id);
    }

    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

//    public List<Employee> findAllUsers() {
//        return employeeDao.findAll();
//    }
//
//    public Auto findAutoById(int id) {
//        return employeeDao.findAutoById(id);
//    }
}
