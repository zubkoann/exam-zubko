package com.zubko.models;

import java.util.Objects;

public class Tech {
    private int id;
    private String type;
    private String name;
    private String model;
    private String date;
    private String userId;

    public Tech(int id,String type, String name, String model, String date) {
        this.id = id;
        this.type = TechTypes.valueOf(type.toUpperCase()).toString();;
        this.name = name;
        this.model = model;
        this.date = date;
    }

    public Tech(int id, String type, String name, String model, String date, String userId) {
        this.id = id;
        this.type = TechTypes.valueOf(type.toUpperCase()).toString();
        this.name = name;
        this.model = model;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
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
