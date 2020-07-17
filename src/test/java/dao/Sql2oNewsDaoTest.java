package dao;

import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;



import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/org_news_portal_test";
        Sql2o sql2o = new Sql2o(connectionString, "User", "7181");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn =sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        newsDao.clearAll();
        departmentDao.clearAll();
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News testNews = setUpNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getNewsInDepartment() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }

    //helpers
    public News setUpNews() {
        return new News("Current Sprints", "We have added 10 more sprints, and happy holidays, kinda");
    }

}