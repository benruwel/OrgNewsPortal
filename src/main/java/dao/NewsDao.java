package dao;

import models.Department;
import models.News;

import java.util.List;

public interface NewsDao {

    //create
    void add(News news);

    //read
    List<News> getAll();
    List<News> getAllNewsInDepartment(int department_id);

    //delete
    void deleteById(int id);
    void clearAll();

}
