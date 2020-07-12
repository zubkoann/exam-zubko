package com.zubko.models;

import java.util.Objects;

public class User {
    private int id;
    private String surname;
    private String name;
    private String email;
    private String role;
    private String team;

    public User(int id, String surname, String name, String email, String role, String team) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.role = role;
        this.team = team;
    }

    public User(String surname, String name, String email, String role, String team) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.role = role;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", team='" + team + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role) &&
                Objects.equals(team, user.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, email, role, team);
    }
}
