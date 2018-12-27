package com.alevel.prokopchuk.hw25.dao;

import com.alevel.prokopchuk.hw25.models.Dish;
import com.alevel.prokopchuk.hw25.models.Ingredient;
import com.alevel.prokopchuk.hw25.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class IngredientsDao {

    private static IngredientsDao instance;

    public static IngredientsDao getInstance() {
        if (instance == null) {
            instance = new IngredientsDao();
        }
        return instance;
    }

    public List<Ingredient> findAll() {
        List<Ingredient> dishes = (List<Ingredient>)  HibernateSessionFactoryUtil.getSessionFactory().
                openSession().createQuery("From Ingredient").list();
        return dishes;
    }

    public Dish getById(int id) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().findAll());
    }
}
