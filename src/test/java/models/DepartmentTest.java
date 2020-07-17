package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void setName() {
        Department testDepartment = setUpDepartment();
        testDepartment.setName("Software Dev");
        assertNotEquals(setUpDepartment().getName(), testDepartment.getName());
    }

    @Test
    public void setId() {
        Department testDepartment = setUpDepartment();
        testDepartment.setId(2);
        assertEquals(2, testDepartment.getId());
    }

    @Test
    public void setDescription() {
        Department testDepartment = setUpDepartment();
        testDepartment.setDescription("We make hot java code like our coffee");
        assertNotEquals(setUpDepartment().getDescription(), testDepartment.getDescription());
    }

    @Test
    public void setNumber_of_employees() {
        Department testDepartment = setUpDepartment();
        testDepartment.setNumber_of_employees(45);
        assertNotEquals(setUpDepartment().getNumber_of_employees(), testDepartment.getNumber_of_employees());
    }

    //helpers
    public Department setUpDepartment() {
        return new Department("Human Resources", "We make sure everybody enjoys working",23);
    }
}