package dao;

import models.Department;
import models.User;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oUserDao implements UserDao {

    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void addUserToDepartment(User user, Department department) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<Department> getAllDepartmentsOfUser(int id) {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
