package dao;



import models.Genre;
import models.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oGenreDaoTest {
    private Sql2oGenreDao genreDao;
    private Sql2oMovieDao movieDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        movieDao = new Sql2oMovieDao(sql2o);
        genreDao = new Sql2oGenreDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() throws Exception {
        Genre genre = setupNewGenre();
        genreDao.add(genre);
        assertEquals(genreDao.getAll().size(), 1);
    }

    @Test
    public void getAll() throws Exception {
        Genre genre = setupNewGenre();
        Genre genre1 = setupNewGenre();
        Genre genre2 = setupNewGenre();
        genreDao.add(genre);
        genreDao.add(genre1);
        genreDao.add(genre2);
        assertEquals(genreDao.getAll().size(), 3);

    }

    @Test
    public void deleteById() throws Exception {
        Genre genre =setupNewGenre();
        Genre genre1 = setupAltGenre();
        genreDao.add(genre);
        genreDao.add(genre1);
        genreDao.deleteById(genre.getId());

        assertEquals(1, genreDao.getAll().size());
        assertEquals(genre1,genreDao.findById(genre1.getId()));
        assertNotEquals(genre,genreDao.findById(genre.getId()));
    }

    @Test
    public void addGenreToMovieAddsTypeCorrectly() throws Exception {
        Movie testMovie = setupMovie();
        Movie altMovie = setupAltMovie();

        movieDao.add(testMovie);
        movieDao.add(altMovie);

        Genre testGenre = setupNewGenre();

        genreDao.add(testGenre);

        genreDao.addGenreToMovie(testGenre, testMovie);
        genreDao.addGenreToMovie(testGenre, altMovie);

        assertEquals(2, genreDao.getAllMoviesForAGenre(testGenre.getId()).size());
    }
    @Test
    public void deleteingMovieAlsoUpdatesJoinTable() throws Exception {

        Movie testMovie = setupMovie();

        movieDao.add(testMovie);

        Genre testGenre = setupNewGenre();
        Genre otherGenreType = new Genre("Japanese");

        genreDao.add(testGenre);
        genreDao.add(otherGenreType);

        genreDao.addGenreToMovie(testGenre, testMovie);
        genreDao.addGenreToMovie(otherGenreType,testMovie);

        genreDao.deleteById(testMovie.getId());
        assertEquals(0, genreDao.getAllMoviesForAGenre(testGenre.getId()).size());

    }


    @Test
    public void getAllMoviesForAGenre() throws Exception {
    }

    public Genre setupNewGenre() {
        return new Genre("Action");
    }

    public Genre setupAltGenre() {
        return new Genre("Comedy");
    }


     public Movie setupMovie(){
        return new Movie("the dark knight", "chrisopher noland","02/03/2012","www.coolmoviestuff.com","/resources/image/uploads/movie_image.jpg");
    }
    public Movie setupAltMovie(){
         return new Movie ("the clown","travis kniht","04/09/2003");
    }
    public Genre setupGenre(){
        return  new Genre("comedy");
    }

}