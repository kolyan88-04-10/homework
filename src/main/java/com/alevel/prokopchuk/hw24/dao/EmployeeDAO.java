package com.alevel.prokopchuk.hw24.dao;

import com.alevel.prokopchuk.hw24.models.Employee;

public interface EmployeeDAO {
    Employee findById(int id);
    void save(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
}
