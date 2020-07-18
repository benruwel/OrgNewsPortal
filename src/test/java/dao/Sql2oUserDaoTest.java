package dao;

import models.Department;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oUserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://ec2-54-197-254-117.compute-1.amazonaws.com:5432/d7b7p9eglt9v67";
        Sql2o sql2o = new Sql2o(connectionString, "tvxcdwtnzkzaty", "cb88a0d6da6e785fcf7a6016fd740c13c12561ee56056526540c3719bdb47d09");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public  void tearDown() throws Exception {
        System.out.println("Clearing database");
        departmentDao.clearAll();
        userDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("Connection closed");
    }

    @Test
    public void addingUserSetsId() {
        User testUser = setUpUser();
        assertNotEquals(0, testUser.getId());
    }

    @Test
    public void UsersReturnsDepartmentsCorrectly() throws Exception {
        Department testDepartment1 = new Department("Human Resources", "We make sure everybody enjoys working",23);
        departmentDao.add(testDepartment1);
        Department testDepartment2 = new Department("Human Resources", "We make sure everybody enjoys working",23);
        departmentDao.add(testDepartment2);

        User testUser = setUpUser();
        userDao.add(testUser);
        userDao.addUserToDepartment(testUser, testDepartment1);
        departmentDao.addDepartmentToUser(testUser, testDepartment2);
        Department[] departments = {testDepartment1, testDepartment2};
        assertEquals(Arrays.asList(departments), userDao.getAllDepartmentsOfUser(testUser.getId()));
    }

    @Test
    public void addedUsersAreReturnedFromGetAll() {
        User testUser = setUpUser();
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void findById() {
        User testUser = setUpUser();
        User otherUser = setUpUser();
        assertEquals(testUser, userDao.findById(testUser.getId()));
    }

    @Test
    public void deleteById() {
        User testUser = setUpUser();
        User otherUser = setUpUser();
        userDao.deleteById(testUser.getId());
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void clearAll() {
        User testUser = setUpUser();
        User otherUser = setUpUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    //helpers
    public Department setUpDepartment() {
        Department department = new Department("Human Resources", "We make sure everybody enjoys working",23);
        departmentDao.add(department);
        return department;
    }

    public User setUpUser() {
        User user = new User("Bob Killmonger","Scrum Master", "Technical Lead");
        userDao.add(user);
        return user;
    }
}