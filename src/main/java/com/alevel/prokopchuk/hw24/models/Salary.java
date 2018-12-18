package com.alevel.prokopchuk.hw24.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "salaries")
public class Salary {


    private int salary;

    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;
}
