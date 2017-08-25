package dao;

import models.Genre;
import models.Movie;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oMovieDaoTest {
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
    public void findById() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void addMovieToGenre() throws Exception {
        String sql = "INSERT INTO movies_genres (movieid, genreid) VALUES (:movieId, :genreId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("movieId", movie.getId())
                    .addParameter("genreId", genre.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
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

}