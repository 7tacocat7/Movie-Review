package dao;

import models.Genre;
import models.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oMovieDaoTest {

    private Connection conn;
    private Sql2oMovieDao movieDao;
    private Sql2oGenreDao genreDao;
    private Sql2oReviewDao reviewDao;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        movieDao = new Sql2oMovieDao(sql2o);
        genreDao = new Sql2oGenreDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() throws Exception {
        Movie movie = setupMovie();
        movieDao.add(movie);
        assertEquals(movieDao.getAll().size(),1);

    }

    @Test
    public void getAll() throws Exception {
        Movie movie1 = setupAltMovie();
        Movie movie2 = setupMovie();
        Movie movie3 = setupAltMovie();
        movieDao.add(movie1);
        movieDao.add(movie2);
        movieDao.add(movie3);
        assertEquals(movieDao.getAll().size(), 3);

    }

    @Test
    public void deleteById() throws Exception {
        Movie movie1 = setupAltMovie();
        movie1.setId(1);
        Movie movie2 = setupMovie();
        movie2.setId(2);
        Movie movie3 = setupAltMovie();
        movie3.setId(47);
        movieDao.add(movie1);
        movieDao.add(movie2);
        movieDao.add(movie3);
        movieDao.deleteById(3);
        assertEquals(2, movieDao.getAll().size());
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }



    @Test
    public void getAllGenresForAMovie() throws Exception {
        Genre testGenre  = new Genre("Seafood");
        genreDao.add(testGenre);

        Genre otherGenre  = new Genre("Bar Food");
        genreDao.add(otherGenre);

        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        movieDao.addMovieToGenre(testMovie,testGenre);
        movieDao.addMovieToGenre(testMovie,otherGenre);

        Genre[] genres = {testGenre, otherGenre}; //oh hi what is this?

        assertEquals(movieDao.getAllGenresForAMovie(testMovie.getId()), Arrays.asList(genres));
    }

    public Movie setupMovie(){
        return new Movie("Jaws", "Steven Spielberg", "June 20, 1975");
    }
    public Movie setupAltMovie(){
        return new Movie("Halloween", "John Carpenter", "October 25, 1978", "http://halloweenmovies.com", "/resources/images/uploads/Boo.jpg");
    }

}