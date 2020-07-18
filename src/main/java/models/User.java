package models;

import java.util.Objects;

public class User {
    private String name;
    private int id;
    private String role;
    private String position;

    public User(String name, String role, String position) {
        this.name = name;
        this.role = role;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                role.equals(user.role) &&
                position.equals(user.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, role, position);
    }
}
