package main.java.com.prokopchuk.hw23;

import main.java.com.prokopchuk.hw23.beans.EmployeeTitleBean;
import main.java.com.prokopchuk.hw23.entities.Department;
import main.java.com.prokopchuk.hw23.entities.Employee;
import main.java.com.prokopchuk.hw23.entities.Gender;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
//    private static final String url = "jdbc:mysql://localhost:3306/employees"+
//            "?verifyServerCertificate=false"+
//            "&useSSL=false"+
//            "&requireSSL=false"+
//            "&useLegacyDatetimeCode=false"+
//            "&amp"+
//            "&serverTimezone=UTC";
//    private static final String user = "root";
//    private static final String password = "";

    public static void main(String[] args) {
        //part1();
        part2();
    }

    private static void part1() {
        List<Department> departments = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        final String sql1 = "select * from departments order by dept_name";
        final String sql2 = "select employees.emp_no, birth_date, first_name, last_name,\n" +
                "gender, hire_date from employees\n" +
                "join titles on employees.emp_no = titles.emp_no\n" +
                "where (year(now() - year(employees.hire_date)) > 20)\n" +
                "group by titles.emp_no having max(titles.to_date) > now();";
        try (Connection connection = ConnectorDB.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql1);
            while(rs.next()){
                departments.add(new Department(rs.getString(1), rs.getString(2)));
            }
            System.out.println(departments);
            rs = st.executeQuery(sql2);
            Employee employee;
            while (rs.next()) {
                int id = rs.getInt(1);
                Date birthDate = rs.getDate(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String genderStr = rs.getString(5);
                Gender gender = Gender.valueOf(genderStr);
                Date hireDate = rs.getDate(6);
                employee = new Employee(id, birthDate, firstName, lastName, gender, hireDate);
                employees.add(employee);
            }
            for (Employee employee1 : employees) {
                System.out.println(employee1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void part2() {
        List<EmployeeTitleBean> employees = new ArrayList<>();
        final String sql = "select employees.emp_no, first_name, last_name, \n" +
                "hire_date, (select title from titles\n" +
                "where titles.to_date > now() and titles.emp_no = employees.emp_no) as current_title,\n" +
                "sum(salary) from employees\n" +
                "join titles on employees.emp_no = titles.emp_no\n" +
                "join salaries on employees.emp_no = salaries.emp_no\n" +
                "where titles.to_date > now()\n" +
                "group by employees.emp_no;";
        try (Connection connection = ConnectorDB.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            EmployeeTitleBean employee;
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                Date hireDate = rs.getDate(4);
                String title = rs.getString(5);
                int totalSalary = rs.getInt(6);
                employee = new EmployeeTitleBean(id, firstName, lastName, title, totalSalary, hireDate);
                employees.add(employee);
            }
            System.out.println(employees.size() + " employees");
            for (EmployeeTitleBean employeePointer : employees) {
                System.out.println(employeePointer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
