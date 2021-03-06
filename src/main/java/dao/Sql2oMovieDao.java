package dao;
import models.Genre;
import models.Movie;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.ArrayList;
import java.util.List;



public class Sql2oMovieDao implements MovieDao {

    private final Sql2o sql2o;

    public Sql2oMovieDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Movie movie) {
        String sql = "INSERT INTO movies (title, director, releaseDate, website, image) VALUES (:title, :director, :releaseDate, :website, :image)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(movie)
                    .executeUpdate()
                    .getKey();
            movie.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Movie> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies")
                    .executeAndFetch(Movie.class);
        }
    }

//    @Override
    public void deleteById(int id) {
        String sql = "DELETE from movies WHERE id = :id"; //raw sql
        String deleteJoin = "DELETE from movies_genres WHERE movieId = :movieId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("movieId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Movie findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Movie.class);
        }
    }

//    @Override
    public void update(String newTitle, String newDirector, String newReleaseDate, String newWebsite, String newImage){
        String sql = "UPDATE movies SET ( title, director,  releaseDate,  website,  image) = (:title, :director,  :releaseDate,  :website,  :image) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", newTitle)
                    .addParameter("director", newDirector)
                    .addParameter("releaseDate", newReleaseDate)
                    .addParameter("website", newWebsite)
                    .addParameter("image", newImage)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addMovieToGenre(Movie movie, Genre genre){
        String sql = "INSERT INTO movies_genres (movieId, genreId) VALUES (:movieId, :genreId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("movieId", movie.getId())
                    .addParameter("genreId", genre.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Genre> getAllGenresForAMovie(int movieId) {
        ArrayList<Genre> genres = new ArrayList<>();

        String joinQuery = "SELECT genreId FROM movies_genres WHERE movieId = :movieId";

        try (Connection con = sql2o.open()) {
            List<Integer> allGenresIds = con.createQuery(joinQuery)
                    .addParameter("movieId", movieId)
                    .executeAndFetch(Integer.class);
            for (Integer genreId : allGenresIds){
                String genreQuery = "SELECT * FROM genres WHERE id = :genreId";
                genres.add(
                        con.createQuery(genreQuery)
                                .addParameter("genreId", genreId)
                                .executeAndFetchFirst(Genre.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return genres;
    }


}
