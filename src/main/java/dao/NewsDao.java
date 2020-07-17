package dao;

import models.Department;
import models.News;

public interface NewsDao {

    //create
    void add(News news);

    //read
    void getAll();
    void getNewsToDepartment(int department_id);

    //delete
    void deleteById(int id);
    void clearAll();

}
