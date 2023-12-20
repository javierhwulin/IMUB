package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RatingValue {
    ComputeValue computeValue;
    public RatingValue(){
        computeValue = new ComputeValue();
    }
    // RatingValue is a class that return a list of the ratings of a film or serie with a key="name" and key="value" structure.
    public List<HashMap<String, String>> getAllFilmRatings(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, String typeCalcul) {
        switch (typeCalcul) {
            case "ValoracioStrategyPromig" -> {
                computeValue.setValorarStrategy(new ComputeByPromig());
                computeValue.executeValoracio(valoracions);
            }
            case "ValoracioStrategyAbsolut" -> {
                computeValue.setValorarStrategy(new ComputeByAbsolut());
                computeValue.executeValoracio(valoracions);
            }
        }
        List<HashMap<String, String>> allRatings = new ArrayList<>();
        for (Quartet<Client, ContingutDigital, Integer, Integer> p : valoracions.keySet()) {
            if(p.getElement2() instanceof Pelicula){
                HashMap<String, String> atributs = new HashMap<>();
                atributs.put("nom", p.getElement2().getNom());
                atributs.put("valor", String.valueOf(valoracions.get(p).getValoracio()));
                allRatings.add(atributs);
            }
        }
        return allRatings;
    }

    public List<HashMap<String, String>> getAllSerieRatings(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, String typeCalcul) {
        List<HashMap<String, String>> allRatings = new ArrayList<>();
        HashSet<ContingutDigital> seriesValorats = new HashSet<>();
        for (Quartet<Client, ContingutDigital, Integer, Integer> s : valoracions.keySet()) {
            if(s.getElement2() instanceof Serie || !seriesValorats.contains(s.getElement2())){
                HashMap<String, String> atributs = new HashMap<>();
                float valor = (float) valoracions.entrySet().stream()
                        .filter(entry -> entry.getKey().getElement2().equals(s.getElement2()))
                        .mapToDouble(entry -> entry.getValue().getValoracio())
                        .sum();
                atributs.put("nom", s.getElement2().getNom());
                atributs.put("valor", String.valueOf(valor));
                allRatings.add(atributs);
                seriesValorats.add(s.getElement2());
            }
        }
        return allRatings;
    }
}
