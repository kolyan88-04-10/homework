package main.java.com.prokopchuk.hw23;

import main.java.com.prokopchuk.hw23.beans.EmployeeTitleBean;
import main.java.com.prokopchuk.hw23.entities.Department;
import main.java.com.prokopchuk.hw23.entities.Employee;
import main.java.com.prokopchuk.hw23.entities.Gender;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        //part2();
        //part3();
        part4(1000000, 1000);
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
            System.out.println("Found employees " + employees.size());
            for (Employee employeePointer : employees) {
                System.out.println(employeePointer);
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
            //int counter = 0;
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                Date hireDate = rs.getDate(4);
                String title = rs.getString(5);
                int totalSalary = rs.getInt(6);
                employee = new EmployeeTitleBean(id, firstName, lastName, title, totalSalary, hireDate);
                //counter++;
                System.out.println(employee);
                employees.add(employee);
            }
            //System.out.println("Found " + counter + " employees");
            System.out.println("Found " + employees.size() + " employees");
            for (EmployeeTitleBean employeePointer : employees) {
                System.out.println(employeePointer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void part3() {
        Scanner scanner = new Scanner(System.in);
        Pattern datePattern = Pattern.compile("(19|2[0-9])[0-9]{2}");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        final String sql = "select employees.emp_no, first_name, last_name,\n" +
                "hire_date from employees\n" +
                "where year(hire_date) = ?;";
        System.out.println("Input the date hire employees, or 0 to exit");
        int date = scanner.nextInt();
        while (date != 0) {
            if (date > 1900 && date <= 2018) {
                //Date date = new Date(dateFormat.parse(stringDate).getTime());
                List<Employee> employees = new ArrayList<>();
                try (Connection connection = ConnectorDB.getConnection();
                     PreparedStatement st = connection.prepareStatement(sql)) {
                    st.setInt(1, date);
                    ResultSet rs = st.executeQuery();
                    Employee employee;
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String firstName = rs.getString(2);
                        String lastName = rs.getString(3);
                        Date hireDate = rs.getDate(4);
                        employee = new Employee(id, firstName, lastName, hireDate);
                        employees.add(employee);
                    }
                    System.out.println(employees.size() + " employees");
                    for (Employee employeePointer : employees) {
                        System.out.println(employeePointer);
                    }
                    System.out.println(employees.size() + " employees found");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Input the date hire employees, or exit");
            }
            System.out.println();
            date = scanner.nextInt();
        }
    }

    private static void part4(int threshold, int increase) {
        List<EmployeeTitleBean> employees = new ArrayList<>();
        final String sqlSelect = "select employees.emp_no, first_name, last_name,\n" +
                "(select title from titles \n" +
                "where titles.to_date > now() and titles.emp_no = employees.emp_no) as current_title,\n" +
                "(select salary from salaries \n" +
                "where salaries.to_date > now() and salaries.emp_no = employees.emp_no) as current_salary,\n" +
                "sum(salary) as total_salary from employees \n" +
                "join titles on employees.emp_no = titles.emp_no\n" +
                "join salaries on employees.emp_no = salaries.emp_no \n" +
                "where titles.to_date > now()\n" +
                "group by employees.emp_no having total_salary > ?;";
        try (Connection connection = ConnectorDB.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sqlSelect);
            st.setInt(1, threshold);
            ResultSet rs = st.executeQuery();
            EmployeeTitleBean employee;
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String title = rs.getString(4);
                int currentSalary = rs.getInt(5);
                int totalSalary = rs.getInt(6);
                employee = new EmployeeTitleBean(id, firstName, lastName, title, currentSalary, totalSalary);
                employees.add(employee);
            }
            for (EmployeeTitleBean employeePointer : employees) {
                System.out.println(employeePointer);
            }
            System.out.println("Found " + employees.size() + " employees");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
