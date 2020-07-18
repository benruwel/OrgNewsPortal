package dao;

import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {

    //create
    void add(Department department);
    void addDepartmentToUser(User user, Department department);

    //read
    List<Department> getAll();
    List<User> getAllUsersInDepartment(int department_id);
    Department findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
