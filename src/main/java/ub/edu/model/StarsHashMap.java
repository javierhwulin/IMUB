package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;

import java.util.HashMap;
import java.util.List;

public class StarsHashMap extends RatingHashMap{
    private final HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> starValoracions;
    private final RatingValue ratingValue;
    private final RatingManager ratingManager;
    public StarsHashMap() {
        starValoracions = new HashMap<>();
        ratingValue = new RatingValue();
        ratingManager = new RatingManager();
    }

    @Override
    public void addFilmRating(imUBCataleg cataleg, Client client, String title, float rating) {
        ratingManager.addFilmRating(starValoracions, cataleg, client, title, rating);
    }

    @Override
    public void addEpisodeRating(imUBCataleg cataleg, Client client, String title, int numTemporada, int numEpisodi, float rating) {
        ratingManager.addEpisodeRating(starValoracions, cataleg, client, title, numTemporada, numEpisodi, rating);
    }

    @Override
    public List<HashMap<String, String>> getAllFilmRatings(ValorType typeCalcul) {
        return ratingValue.getAllFilmRatings(starValoracions, typeCalcul);
    }

    @Override
    public List<HashMap<String, String>> getAllSerieRatings(ValorType typeCalcul) {
        return ratingValue.getAllSerieRatings(starValoracions, typeCalcul);
    }
}
