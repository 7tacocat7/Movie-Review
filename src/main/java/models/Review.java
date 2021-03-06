package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Review {

    private int id;
    private String writtenBy;
    private int rating;
    private Timestamp createdAt;
    private String content;
    private int movieId;

    public Review(String writtenBy, int rating, String content, int movieId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.content = content;
        this.movieId = movieId;
    }
//getters

    public int getId() {
        return id;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public String getCreatedAt() {
        Date createdAtAsDate = this.createdAt;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(createdAtAsDate);
        return formattedDate;
    }

    public String getContent() {
        return content;
    }

    public int getMovieId() {
        return movieId;
    }

    //setters


    public void setId(int id) {
        this.id = id;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}


