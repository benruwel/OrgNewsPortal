package dao;

import models.Department;
import models.News;
import org.junit.*;
import org.sql2o.Sql2o;
import org.sql2o.Connection;



import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static Connection conn;
    private static Sql2oNewsDao newsDao;
    private static Sql2oDepartmentDao departmentDao;

    @BeforeClass
    public static void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://ec2-54-197-254-117.compute-1.amazonaws.com:5432/d7b7p9eglt9v67";
        Sql2o sql2o = new Sql2o(connectionString, "tvxcdwtnzkzaty", "cb88a0d6da6e785fcf7a6016fd740c13c12561ee56056526540c3719bdb47d09");

        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        departmentDao.clearAllJoinTable();
        newsDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("Closing connection");
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News testNews = setUpNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void addedNewsAreReturnedFromGetAll() throws Exception {
        News testNews = setUpNews();
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void getNewsInDepartment() {
        Department testDepartment = setUpDepartment();
        News testNews1 = setUpNewsForDepartment(testDepartment);
        News testNews2 = setUpNewsForDepartment(testDepartment);
        assertEquals(2, newsDao.getAllNewsInDepartment(testDepartment.getId()).size());

    }

    @Test
    public void deleteById() {
        News testNews = setUpNews();
        News testNews2 = setUpNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(testNews.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() {
        News testNews = setUpNews();
        News testNews2 = setUpNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    //helpers
    public News setUpNews() {
        News news = new News("Current Sprints", "We have added 10 more sprints, and happy holidays, kinda", 3);
        newsDao.add(news);
        return news;
    }

    public News setUpNewsForDepartment(Department department) {
        News news = new News("Current Sprints", "We have added 10 more sprints, and happy holidays, kinda", department.getId());
        newsDao.add(news);
        return news;
    }

    public Department setUpDepartment() {
        Department department = new Department("Human Resources", "We make sure everybody enjoys working",23);
        departmentDao.add(department);
        return department;
    }

}