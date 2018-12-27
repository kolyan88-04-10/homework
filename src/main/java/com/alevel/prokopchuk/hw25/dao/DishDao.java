package com.alevel.prokopchuk.hw25.dao;

import com.alevel.prokopchuk.hw25.models.Dish;
import com.alevel.prokopchuk.hw25.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DishDao {
    private static DishDao instance;

    //private List<Dish> menuForTest = new ArrayList<>();

    public static DishDao getInstance() {
        if (instance == null) {
            instance = new DishDao();
        }
        return instance;
    }

    public List<Dish> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession();
        List<Dish> dishes = session.createQuery("From Dish").list();
        return dishes;
    }

    public Dish getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().get(Dish.class, id);
    }

    public void save(Dish dish) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(dish);
        tx1.commit();
        session.close();
    }

    public static void main(String[] args) {
        System.out.println(getInstance().findAll());
    }
}
