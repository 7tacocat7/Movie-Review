package dao;

import models.Genre;
import models.Movie;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oGenreDaoTest {
    @Test
    public void add() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
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
        Genre testGenre  = new Genre("Seafood");
        genreDao.restaurant
restaurant
restaurant
restaurant
restaurant
restaurant
restaurantadd(testGenre);

        Movie testMovie = setupMovie();
        movieDao.add(testMovie);

        Movie altMovie = setupAltMovie();
        movieDao.add(altMovie);

        movieDao.addMovieToGenre(testMovie,testGenre);
        movieDao.addMovieToGenre(altMovie, testGenre);

        movieDao.deleteById(testMovie.getId());
        assertEquals(0, movieDao.getAllGenresForAMovie(testMovie.getId()).size());
    }


    @Test
    public void getAllMoviesForAGenre() throws Exception {
    }

     public Movie setupMovie(){
        return new Movie("the dark knight", "chrisopher noland","02/03/2012","www.coolmoviestuff.com","/resources/image/uploads/movie_image.jpg");
    }
    public Movie setupAltMovie(){
         return new Movie ("the clown","travis kniht","04/09/2003");
    }

}