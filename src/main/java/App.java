package dao;
import com.google.gson.Gson;
import dao.Sql2oGenreDao;
import dao.Sql2oMovieDao;
import models.Movie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oGenreDao genreDao;
        Sql2oMovieDao movieDao;
        Sql2oReviewDao reviewDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //check me!

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        movieDao = new Sql2oMovieDao(sql2o);
        genreDao = new Sql2oGenreDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();


        post("/movies/new", "application/json", (req, res) -> {
            Movie movie = gson.fromJson(req.body(), Movie.class);
            movieDao.add(movie);
            res.status(201);
            res.type("application/json");
            return gson.toJson(movie);
        });
        get("/movies/new", "application/json", (req, res) ->{
            res.type("application/json");
            return gson.toJson(movieDao.getAll());
        });
        get("/movies/:id", "application/json", (req, res)-> {
            res.type("application/json");
            int movieId =Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(movieDao.findById(movieId));
        });

    }


}
