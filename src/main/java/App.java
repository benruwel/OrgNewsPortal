import com.google.gson.Gson;
import dao.Sql2oUserDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import exceptions.ApiException;
import models.News;
import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        port(getHerokuAssignedPort());

        String connectionString = "jdbc:postgresql://ec2-54-197-254-117.compute-1.amazonaws.com:5432/da2kceiu00u6ln";
        Sql2o sql2o = new Sql2o(connectionString, "oindbdsvcpowkc", "fc7a02358bef630513a4088bba2a9e85a0b21b5006d43b8ac72289b0fa6a948e");

        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        //DEPARTMENTS
        post("/department/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            return gson.toJson(department);
        });

        get("/departments", "application/json", (req, res) -> {
            if(departmentDao.getAll().size() > 0){
                return gson.toJson(departmentDao.getAll());
            }
            else {
                throw new ApiException(404, "No department are currently listed");
            }
        });

        get("/department/:id", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);

            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: %s exists", req.params("id")));
            }

            return gson.toJson(departmentToFind);
        });

        delete("department/:department_id", (req, res) -> {
            int department_id = Integer.parseInt(req.params("department_id"));
            Department departmentToDelete = departmentDao.findById(department_id);
            departmentDao.deleteById(department_id);
            if (departmentToDelete == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(departmentToDelete);
        });

        //USERS
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        get("/users", "application/json", (req, res) -> {
            return gson.toJson(userDao.getAll());
        });

        //NEWS
        post("/departments/:department_id/news/new", "application/json", (req, res) -> {
            int department_id = Integer.parseInt(req.params("department_id"));
            News news = gson.fromJson(req.body(), News.class);

            news.setDepartment_id(department_id);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        get("/departments/:department_id/news", "application/json", (req, res) -> {
            int department_id = Integer.parseInt(req.params("department_id"));
            return gson.toJson(newsDao.getAllNewsInDepartment(department_id));
        });

        //USERS_DEPARTMENTS
        post("/departments/:department_id/user/:user_id/new", "application/json", (req, res) -> {
            int department_id = Integer.parseInt(req.params("department_id"));
            int user_id = Integer.parseInt(req.params("user_id"));
            Department department = departmentDao.findById(department_id);
            User user = userDao.findById(user_id);

            if (department != null && user != null){
                userDao.addUserToDepartment(user, department);
                res.status(201);
                return gson.toJson(String.format("Employee '%s' has joined department '%s'",user.getName(), department.getName()));
            }
            else {
                throw new Exception();
            }
            });

        get("/departments/:department_id/users", "application/json", (req, res) -> {
            int department_id = Integer.parseInt(req.params("department_id"));
            Department departmentToFind = departmentDao.findById(department_id);
            if (departmentToFind == null) {
                throw new Exception();
            } else if (departmentDao.getAllUsersInDepartment(department_id).size() == 0) {
                return "{\"message\":\"There are no employees in this department.\"}";
            } else {
                return gson.toJson(departmentDao.getAllUsersInDepartment(department_id));
            }
            });

        get("/user/:user_id/departments", "application/json", (req, res) -> {
            int user_id = Integer.parseInt(req.params("user_id"));
            User userToFind = userDao.findById(user_id);
            if (userToFind == null){
                throw new Exception();
            }
            else if (userDao.getAllDepartmentsOfUser(user_id).size() == 0){
                return "{\"message\":\"This employee is currently not associated with any department\"}";
            }
            else {
                return gson.toJson(userDao.getAllDepartmentsOfUser(user_id));
            }
        });

        //FILTERS

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) ->{
            res.type("application/json");
        });


    }

}
