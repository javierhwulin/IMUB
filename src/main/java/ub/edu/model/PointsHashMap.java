package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;

import java.util.HashMap;
import java.util.List;

public class PointsHashMap extends RatingHashMap{
    private final HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> pointValoracions;
    private final RatingValue ratingValue;
    private final RatingManager ratingManager;
    public PointsHashMap() {
        pointValoracions = new HashMap<>();
        ratingValue = new RatingValue();
        ratingManager = new RatingManager();
    }

    @Override
    public void addFilmRating(imUBCataleg cataleg, Client client, String title, float rating) {
        ratingManager.addFilmRating(pointValoracions, cataleg, client, title, rating, ValorType.VALORAR_PER_PUNTS.toString());
    }

    @Override
    public void addEpisodeRating(imUBCataleg cataleg, Client client, String title, int numTemporada, int numEpisodi, float rating) {
        ratingManager.addEpisodeRating(pointValoracions, cataleg, client, title, numTemporada, numEpisodi, rating, ValorType.VALORAR_PER_PUNTS.toString());
    }

    @Override
    public List<HashMap<String, String>> getAllFilmRatings(ValorType typeCalcul) {
        return ratingValue.getAllFilmRatings(pointValoracions, typeCalcul);
    }

    @Override
    public List<HashMap<String, String>> getAllSerieRatings(ValorType typeCalcul) {
        return ratingValue.getAllSerieRatings(pointValoracions, typeCalcul);
    }
}
