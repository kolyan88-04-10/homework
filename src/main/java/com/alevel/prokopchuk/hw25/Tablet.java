package com.alevel.prokopchuk.hw25;

import com.alevel.prokopchuk.hw25.kitchen.Order;
import com.alevel.prokopchuk.hw25.models.Dish;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        Order order = new Order(this);
        try {
            List<Dish> dishes = ConsoleHelper.getAllDishesForOrder();
            order.setDishes(dishes);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "console is unavailable");
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
