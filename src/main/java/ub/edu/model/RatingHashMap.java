package ub.edu.model;
import java.util.HashMap;
import java.util.List;

public abstract class RatingHashMap{
    public abstract void addFilmRating(imUBCataleg cataleg, Client client, String title, float rating);

    public abstract void addEpisodeRating(imUBCataleg cataleg, Client client, String title, int numTemporada, int numEpisodi, float rating);

    public abstract List<HashMap<String, String>> getAllFilmRatings(ValorType typeCalcul);
    public abstract List<HashMap<String, String>> getAllSerieRatings(ValorType typeCalcul);
}
