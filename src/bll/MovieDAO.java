package bll;

import bll.util.Reader;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO extends Reader {

    protected List<Movie> movies = new ArrayList<>();

    public void loadFile(String path) {
        super.loadFile(path);
        parseMovies();

    }

    public void parseMovies() {
        for (String str : getInputLines()) {
            var split = str.split(",");

            if (split.length == 3) {
                var result = new Movie();
                var movie_id = Integer.parseInt(split[0]);
                var movie_year = Integer.parseInt(split[1]);
                var movie_name = split[2];

                result.setId(movie_id);
                result.setName(movie_name);
                result.setYear(movie_year);

            }
        }
    }

    public Movie getMove(int id) {
        Movie result = new Movie();
        for (var movie : movies)
            if (id == movie.getId())
                result = movie;
        return result;
    }

    public void addMovie(String name, int year) {
        if (!name.isEmpty() && year > 0)
            movies.add(new Movie(name, year));
    }

    public void removeMovie(String name) {
        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            if (name == movie.getName())
                movies.remove(i);
        }
    }
}
