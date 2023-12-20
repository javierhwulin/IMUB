package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;
import java.util.HashMap;
import java.util.List;

public class LikesHashMap extends RatingHashMap{
    private final HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> likeValoracions;
    private final RatingValue ratingValue;
    private final RatingManager ratingManager;
    public LikesHashMap() {
        likeValoracions = new HashMap<>();
        ratingValue = new RatingValue();
        ratingManager = new RatingManager();
    }

    @Override
    public void addFilmRating(imUBCataleg cataleg, Client client, String title, float rating) {
        ratingManager.addFilmRating(likeValoracions, cataleg, client, title, rating);
    }

    @Override
    public void addEpisodeRating(imUBCataleg cataleg, Client client, String title, int numTemporada, int numEpisodi, float rating) {
        ratingManager.addEpisodeRating(likeValoracions, cataleg, client, title, numTemporada, numEpisodi, rating);
    }

    public List<HashMap<String, String>> getAllFilmRatings(String typeCalcul) {
        return ratingValue.getAllFilmRatings(likeValoracions, typeCalcul);
    }

    public List<HashMap<String, String>> getAllSerieRatings(String typeCalcul) {
        return ratingValue.getAllSerieRatings(likeValoracions, typeCalcul);
    }
}
