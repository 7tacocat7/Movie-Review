package dao;
import models.Review;
import models.Movie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oGenreDao implements GenreDao  {
    private final Sql2o sql2o;

    public Sql2oGenreDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Genre genre) {
        String sql = "INSERT INTO genres (name) VALUES (:name)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(genre)
                    .executeUpdate()
                    .getKey();
            genre.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Genre> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM genres")
                    .executeAndFetch(Genre.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from genres WHERE id=:id";
        String deleteJoin = "DELETE from movies_genres WHERE genreid = :genreId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

            con.createQuery(deleteJoin)
                    .addParameter("genreId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addGenreToRestaurant(Genre genre, Restaurant restaurant){
        String sql = "INSERT INTO movies_genres (restaurantid, genreid) VALUES (:restaurantId, :genreId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantId", restaurant.getId())
                    .addParameter("genreId", genre.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurantsForAGenre(int genreId) {

        ArrayList<Restaurant> movies = new ArrayList<>();

        String joinQuery = "SELECT restaurantid FROM movies_genres WHERE genreid = :genreId";

        try (Connection con = sql2o.open()) {
            List<Integer> allRestaurantIds = con.createQuery(joinQuery)
                    .addParameter("genreId", genreId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer restaurantId : allRestaurantIds){
                String restaurantQuery = "SELECT * FROM movies WHERE id = :restaurantId";
                movies.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("restaurantId", restaurantId)
                                .executeAndFetchFirst(Restaurant.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return movies;
    }


}
