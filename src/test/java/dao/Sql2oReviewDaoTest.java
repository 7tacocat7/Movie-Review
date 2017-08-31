package dao;

import org.junit.Test;
import models.Movie;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {

    private Connection conn;
    private Sql2oReviewDao reviewDao;
    private Sql2oMovieDao movieDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        movieDao = new Sql2oMovieDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void add() throws Exception {
        Review review = setupReview();
        reviewDao.add(review);
        assertEquals(reviewDao.getAll().size(),1);
    }




    @Test
    public void addingReviewSetsId() throws Exception {
        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        Review testReview = new Review("Captain Kirk", 3, "hor",testMovie.getId());
        int originalReviewId = testReview.getId();
        reviewDao.add(testReview);
        assertNotEquals(originalReviewId,testReview.getId());
    }

    @Test
    public void deleteById() throws Exception {
        Review review = setupReview();
        Review review2 = setupReview();
        review.setId(1);
        reviewDao.add(review);
        reviewDao.add(review2);
        reviewDao.deleteById(1);
        assertEquals(reviewDao.getAll().size(),1);

    }

    @Test
    public void getAllReviewsByMovie() throws Exception {

        Movie testMovie = setupMovie();
        movieDao.add(testMovie);

        Review testReview = new Review("Captain Kirk", 3, "worst movie ever",testMovie.getId());
        reviewDao.add(testReview);

        assertEquals(1, reviewDao.getAllReviewsByMovie(testMovie.getId()).size());
    }

    //helpers


    public Movie setupMovie() {
        return new Movie("the dark knight", "chrisopher noland", "02/03/2012", "www.coolmoviestuff.com", "/resources/image/uploads/movie_image.jpg");
    }
    public Review setupReview(){
        return  new Review("carson", 2,"this movie is garbage",7);

    }

}