package dao;

import models.Review;
import models.Movie;
import models.Genre;
import java.util.List;

public interface ReviewDao {

    //create
    void add(Review review);

    //read
    List<Review> getAllReviewsByMovie(int restaurantId);
    List<Review>getAll();

    //update
    //omit for now

    //delete
    void deleteById(int id);
}

