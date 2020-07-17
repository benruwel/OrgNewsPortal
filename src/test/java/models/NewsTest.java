package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {


    @Test
    public void getId() {
        News testNews = setUpNews();
        testNews.setId(2);
        assertEquals(2, testNews.getId());
    }

    @Test
    public void getTitle() {
        News testNews = setUpNews();
        assertEquals("Current Sprints", testNews.getTitle());
    }

    @Test
    public void setTitle() {
        News testNews = setUpNews();
        testNews.setTitle("New interns");
        assertNotEquals("Intern training", testNews.getTitle());
    }

    @Test
    public void getContent() {
        News testNews = setUpNews();
        assertEquals("We have added 10 more sprints, and happy holidays, kinda", testNews.getContent());
    }

    @Test
    public void setContent() {
        News testNews = setUpNews();
        testNews.setContent("Let's welcome the interns like family");
        assertNotEquals("Please fill a form to volunteer for intern training", testNews.getContent());
    }

    //helpers
    public News setUpNews() {
        return new News("Current Sprints", "We have added 10 more sprints, and happy holidays, kinda");
    }

}