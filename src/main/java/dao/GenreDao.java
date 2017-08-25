package dao;

import models.Genre;
import models.Movie;

import java.util.List;

public interface GenreDao {

    //create
    void add(Genre genre);
    void addGenreToMovie(Genre genre, Movie movie);

    //read
    List<Genre> getAll();  it should be implemented.
    List<Movie> getAllMoviesForAGenre(int id);

    //update


    //delete
    void deleteById(int id);
}
}
