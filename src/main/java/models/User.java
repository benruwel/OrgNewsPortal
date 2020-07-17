package models;

import java.util.Objects;

public class User {
    private String name;
    private int id;
    private int department_id;
    private String roles;
    private String position;

    public User(String name, int department_id, String roles, String position) {
        this.name = name;
        this.department_id = department_id;
        this.roles = roles;
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

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
                department_id == user.department_id &&
                name.equals(user.name) &&
                roles.equals(user.roles) &&
                position.equals(user.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, department_id, roles, position);
    }
}
