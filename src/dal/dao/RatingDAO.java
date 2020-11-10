package dal.dao;

import dal.Rating;
import dal.util.Reader;

import java.util.ArrayList;
import java.util.List;

public class RatingDAO extends Reader {

    protected List<Rating> ratings = new ArrayList<>();

    @Override
    public void loadFile(String path) {
        super.loadFile(path);
        parseratings();
    }

    @Override
    public void saveFile() {
        for (var rating : ratings)
            outputLines.add(String.format("%d,%s", rating.getUserId(), rating.getRating()));

        super.saveFile();
    }

    public void parseratings() {
        for (String str : getInputLines()) {
            var split = str.split(",");

            if (split.length == 3) {
                var result = new Rating();
                var movie_id = Integer.parseInt(split[0]);
                var rating = Integer.parseInt(split[1]);
                var user_id = Integer.parseInt(split[2]);

                result.setMovieId(movie_id);
                result.setUserId(user_id);
                result.setRating(rating);
            }
        }
    }

    public Rating getRating(int id) {
        Rating result = new Rating();
        for (var rating : ratings)
            if (id == rating.getRating())
                result = rating;
        return result;
    }

    public void addRating(int movieId, int userId, int rating) {
        if (movieId != -1 && userId != -1) {
            int new_id = ratings.size() + 1;
            ratings.add(new Rating(movieId, userId, rating));
        }
    }

    public void removeRating(int movieId) {
        for (int i = 0; i < ratings.size(); i++) {
            var rating = ratings.get(i);
            if (movieId == rating.getMovieId())
                ratings.remove(i);
        }
    }

    public void removeRating(int userId, int movieId) {
        for (int i = 0; i < ratings.size(); i++) {
            var rating = ratings.get(i);
            if (movieId == rating.getMovieId() && rating.getUserId() == userId)
                ratings.remove(i);
        }
    }
}
