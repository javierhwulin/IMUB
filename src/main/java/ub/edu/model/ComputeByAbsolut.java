package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;
public class ComputeByAbsolut implements ValorarStrategy{
    @Override
    public HashMap<String, Float> executeValoracio(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> allRatings) {
        HashMap<String, Float> result = new HashMap<>();
        for (Quartet<Client, ContingutDigital, Integer, Integer> key : allRatings.keySet()) {
            String title = key.getElement2().getNom();
            float rating = allRatings.get(key).getValoracio();
            if (result.containsKey(title)) {
                float newRating = result.get(title) + rating;
                result.put(title, newRating);
            } else {
                result.put(title, rating);
            }
        }
        return result;
    }
}
