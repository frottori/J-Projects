public class Movie {
    String code;
    String title;
    String releaseDate;
    int duration;
    String category;
    String director;
    String language;
    double rating;

    public Movie() {
    }

    public Movie(String code, String title, String releaseDate, int duration, String category, String director, String language, double rating) {
        this.code = code;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.category = category;
        this.director = director;
        this.language = language;
        this.rating = rating;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", duration='" + getDuration() + "'" +
            ", category='" + getCategory() + "'" +
            ", director='" + getDirector() + "'" +
            ", language='" + getLanguage() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }
}