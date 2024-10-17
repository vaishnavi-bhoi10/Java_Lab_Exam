package EntityCinema;

public class Movie {
	 private int movieId;
	    private String title;

	    public Movie(int movieId, String title) {
	        this.movieId = movieId;
	        this.title = title;
	    }

	    public int getMovieId() {
	        return movieId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    @Override
	    public String toString() {
	        return "Movie ID: " + movieId + ", Title: " + title;
	    }
	}
