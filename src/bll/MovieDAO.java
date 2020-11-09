package bll;

import bll.util.Reader;

public class MovieDAO extends Reader {

    public Movie getMove(int id) {
        Movie result = new Movie();
        for (String str : getInputLines()) {
            var split = str.split(",");

            if (split.length == 3) {
                var movie_id = Integer.parseInt(split[0]);
                var movie_year = Integer.parseInt(split[1]);
                var movie_name = split[2];

                result.setId(movie_id);
                result.setName(movie_name);
                result.setYear(movie_year);

                if (movie_id == id)
                    return result;
            }
        }

        return null;
    }
}
