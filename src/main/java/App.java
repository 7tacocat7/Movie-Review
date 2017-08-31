package dao;
import com.google.gson.Gson;
import dao.Sql2oGenreDao;
import dao.Sql2oMovieDao;
import exceptions.ApiException;
import models.Movie;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

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

        //create
        post("/movies/new", "application/json", (req, res) -> {
            Movie movie = gson.fromJson(req.body(), Movie.class);
            movieDao.add(movie);
            res.status(201);
            res.type("application/json");
            return gson.toJson(movie);
        });

        post("/movies/:movieId/reviews/new", "application/json", (req, res) ->{
            int movieId = Integer.parseInt(req.params("movieId"));

            Review review = gson.fromJson(req.body(), Review.class);

            review.setMovieId((movieId));
            reviewDao.add(review);
            res.status(201);
            return gson.toJson((review));
        });
        //read
        get("/movies", "application/json", (req, res) ->{
            res.type("application/json");
            return gson.toJson(movieDao.getAll());
        });
        get("/movies/:id", "application/json", (req, res) -> {
            int movieId = Integer.parseInt(req.params("id"));
            Movie movieToFind = movieDao.findById(movieId);
            if (movieToFind == null) {
                throw new ApiException(404, String.format("No movie with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(movieToFind);
        });

        get("/movies/:id", "application/json", (req, res)-> {
            res.type("application/json");
            int movieId =Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(movieDao.findById(movieId));
        });

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        //Filters
        after((req, res)-> {
            res.type("application/json");
        });

    }


}
