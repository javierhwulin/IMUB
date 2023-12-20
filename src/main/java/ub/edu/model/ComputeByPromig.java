package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;

public class ComputeByPromig implements ValorarStrategy{
    @Override
    public HashMap<String, Float> executeValoracio(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> allRatings) {
        HashMap<String, Float> result = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();
        for (Quartet<Client, ContingutDigital, Integer, Integer> key : allRatings.keySet()) {
            String title = key.getElement2().getNom();
            float rating = allRatings.get(key).getValoracio();
            if (result.containsKey(title)) {
                float newRating = result.get(title) + rating;
                result.put(title, newRating);
                count.put(title, count.get(title) + 1);
            } else {
                result.put(title, rating);
                count.put(title, 1);
            }
        }
        result.replaceAll((t, v) -> result.get(t) / count.get(t));
        return result;
    }
}
