package com.alevel.prokopchuk.hw23.entities;

public abstract class Entity {
    private int id;

    public Entity() {}

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
