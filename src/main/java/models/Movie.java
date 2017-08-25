package models;




public class Movie {

    private String title;
    private String director;
    private String releaseDate;
    private String website;
    private String image;
    private int id;


    public Movie(String title, String director, String releaseDate) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.website = "no website listed";
        this.image = "/resources/images/uploads/no_image.jpg";

    }

    public Movie(String title, String director, String releaseDate, String website, String image) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.website = website;
        this.image = image;

    }
    //getters


    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public String getWebsite() {
        return website;
    }


    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    //setters


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public void setWebsite(String website) {
        this.website = website;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (!title.equals(movie.title)) return false;
        if (!director.equals(movie.director)) return false;
        if (!releaseDate.equals(movie.releaseDate)) return false;
        if (!website.equals(movie.website)) return false;
        return image.equals(movie.image);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + id;
        return result;
    }
}

