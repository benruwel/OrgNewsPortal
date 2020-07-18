package dao;

import models.Department;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import javax.swing.plaf.synth.SynthTextAreaUI;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {

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
        departmentDao.clearAllJoinTable();
        userDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("Connection closed");
    }

    @Test
    public void addingDepartmentSetsId() {
        Department testDepartment = setUpDepartment();
        assertNotEquals(0, testDepartment.getId());
    }

    @Test
    public void DepartmentReturnsUsersCorrectly() throws Exception {
        User testUser1 = new User("Rich Mpotevu", "Outreach Coordinator", "Project Lead");
        userDao.add(testUser1);
        User testUser2 = new User("Papa Wemba", "Community Representative", "Extra");
        userDao.add(testUser2);

        Department testDepartment = setUpDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addDepartmentToUser(testUser1, testDepartment);
        departmentDao.addDepartmentToUser(testUser2, testDepartment);
        User[] users = {testUser1, testUser2};
        assertEquals(Arrays.asList(users), departmentDao.getAllUsersInDepartment(testDepartment.getId()));
    }

    @Test
    public void addedDepartmentsAreReturnedByGetAll() {
        Department testDepartment = setUpDepartment();
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectDepartment() {
        Department testDepartment = setUpDepartment();
        Department otherRestaurant = setUpDepartment();
        assertEquals(testDepartment, departmentDao.findById(testDepartment.getId()));
    }

    @Test
    public void deleteByIdDeletesCorrectlyDepartment() {
        Department testRestaurant = setUpDepartment();
        Department otherRestaurant = setUpDepartment();
        departmentDao.deleteById(testRestaurant.getId());
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void clearAllDeletesAllRecords() {
        Department testRestaurant = setUpDepartment();
        Department otherRestaurant = setUpDepartment();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
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