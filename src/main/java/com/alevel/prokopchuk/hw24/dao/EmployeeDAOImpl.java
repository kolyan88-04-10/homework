package com.alevel.prokopchuk.hw24.dao;

import com.alevel.prokopchuk.hw24.models.Employee;
import com.alevel.prokopchuk.hw24.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public Employee findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employee);
        tx1.commit();
        session.close();
    }
}
