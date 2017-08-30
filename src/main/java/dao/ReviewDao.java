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

    //update
    //omit for now

    //delete
    void deleteById(int id);
}

