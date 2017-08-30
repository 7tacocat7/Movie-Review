package dao;

import models.Genre;
import models.Movie;

import java.util.List;

public interface MovieDao {
    //create
    void add (Movie movie); //L
    void addMovieToGenre(Movie movie, Genre genre); //D

    //read
    List<Movie> getAll(); //A
    List<Genre> getAllGenresForAMovie(int movieId); //D - we will implement this soon.

    Movie findById(int id); //B

//    //update
//    void update(String title, String director, String releaseDate, String website, String image); //N
//
//    //delete
//    void deleteById(int id); //M
}

