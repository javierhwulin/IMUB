package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;

import java.util.*;

public class RatingValue {
    ComputeValue computeValue;
    public RatingValue(){
        computeValue = new ComputeValue();
    }
    // RatingValue is a class that return a list of the ratings of a film or serie with a key="name" and key="value" structure.
    public List<HashMap<String, String>> getAllFilmRatings(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, ValorType typeCalcul) {
        HashMap<ContingutDigital, Float> ratings = getValorList(valoracions, typeCalcul);
        List<HashMap<String, String>> allRatings = new ArrayList<>();

        for (Map.Entry<ContingutDigital, Float> entry : ratings.entrySet()) {
            if(entry.getKey() instanceof Pelicula){
                HashMap<String, String> atributs = new HashMap<>();
                atributs.put("nom", entry.getKey().getNom());
                atributs.put("valor", String.valueOf(entry.getValue()));
                allRatings.add(atributs);
            }
        }
        return allRatings;
    }

    public List<HashMap<String, String>> getAllSerieRatings(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, ValorType typeCalcul) {
        HashMap<ContingutDigital, Float> ratings = getValorList(valoracions, typeCalcul);
        List<HashMap<String, String>> allRatings = new ArrayList<>();
        for (Map.Entry<ContingutDigital, Float> entry : ratings.entrySet()) {
            if(entry.getKey() instanceof Serie){
                HashMap<String, String> atributs = new HashMap<>();
                atributs.put("nom", entry.getKey().getNom());
                atributs.put("valor", String.valueOf(entry.getValue()));
                allRatings.add(atributs);
            }
        }
        return allRatings;
    }

    private HashMap<ContingutDigital, Float> getValorList(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, ValorType typeCalcul) {
        HashMap<ContingutDigital, Float> valorMap = null;
        switch(typeCalcul) {
            case VALORAR_PER_PROMIG -> {
                computeValue.setValorarStrategy(new ComputeByPromig());
                valorMap = computeValue.executeValoracio(valoracions);
            }
            case VALORAR_PER_ABSOLUT -> {
                computeValue.setValorarStrategy(new ComputeByAbsolut());
                valorMap = computeValue.executeValoracio(valoracions);
            }
        }
        return valorMap;
    }
}
