package com.alevel.prokopchuk.hw25.kitchen;

import com.alevel.prokopchuk.hw25.Tablet;
import com.alevel.prokopchuk.hw25.models.Dish;

import java.util.List;

public class Order {
    private final Tablet tablet;
    private List<Dish> dishes;


    public Order(Tablet tablet) {
        this.tablet = tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Your Order is {" +
                "dishes=" + dishes +
                " of tablet=" + tablet +
                '}';
    }
}
