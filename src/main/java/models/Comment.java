package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Guest on 8/30/17.
 */
public class Comment extends Review {
    private String userName;

    public Comment(String writtenBy, int rating, String content, int restaurantId, String userName) {
        super(writtenBy, rating, content, restaurantId);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
