package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void setName() {
        User testUser = setUpUser();
        testUser.setName("Robert Killmonger");
        assertNotEquals(setUpUser().getName(), testUser.getName());
    }

    @Test
    public void setId() {
        User testUser = setUpUser();
        testUser.setId(2);
        assertEquals(2, testUser.getId());
    }

    @Test
    public void setRoles() {
        User testUser = setUpUser();
        testUser.setRole("Backend Engineer");
        assertEquals("Backend Engineer", testUser.getRole());
    }

    @Test
    public void setPosition() {
        User testUser = setUpUser();
        testUser.setPosition("Junior Dev");
        assertEquals("Junior Dev", testUser.getPosition());
    }

    //helpers
    public User setUpUser() {
        return new User("Bob Killmonger","Scrum Master", "Technical Lead");
    }
}