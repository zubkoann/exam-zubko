package com.zubko.models;

import java.util.Objects;

public class Tech {
    private int id;
    private String type;
    private String name;
    private String model;
    private String date;
    private int userId;

    public Tech(String type, String name, String model, String date) {
        this.type = TechTypes.valueOf(type.toUpperCase()).toString();
        this.name = name;
        this.model = model;
        this.date = date;
    }

    public Tech(int id, String type, String name, String model, String date) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.model = model;
        this.date = date;
    }

    public Tech(int id, String type, String name, String model, String date, int userId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.model = model;
        this.date = date;
        this.userId = userId;
    }

    public Tech(String type, String name, String model, String date, int userId) {
        this.type = TechTypes.valueOf(type.toUpperCase()).toString();
        this.name = name;
        this.model = model;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Tech{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tech tech = (Tech) o;
        return Objects.equals(type, tech.type) &&
                Objects.equals(name, tech.name) &&
                Objects.equals(model, tech.model) &&
                Objects.equals(date, tech.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, model, date);
    }
}
