package com.alevel.prokopchuk.hw25.dao;

import com.alevel.prokopchuk.hw25.models.Order;
import com.alevel.prokopchuk.hw25.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao {

    private static OrderDao instance;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    public void save(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(order);
        tx1.commit();
        session.close();
    }

    public List<Order> findAll() {
        //Session session = null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Order> orders = (List<Order>) session.createQuery("From Order").list();
        return orders;

    }

    public static void main(String[] args) {
        OrderDao dao = OrderDao.getInstance();
        System.out.println(dao.findAll());
    }
}
