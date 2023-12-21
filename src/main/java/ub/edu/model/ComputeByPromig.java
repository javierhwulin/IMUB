package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;

public class ComputeByPromig implements ValorarStrategy{
    @Override
    public HashMap<ContingutDigital, Float> executeValoracio(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> allRatings) {
        HashMap<ContingutDigital, Float> result = new HashMap<>();
        HashMap<ContingutDigital, Integer> count = new HashMap<>();
        for (Quartet<Client, ContingutDigital, Integer, Integer> key : allRatings.keySet()) {
            ContingutDigital contingutDigital = key.getElement2();
            float rating = allRatings.get(key).getValoracio();
            if (result.containsKey(contingutDigital)) {
                float newRating = result.get(contingutDigital) + rating;
                result.put(contingutDigital, newRating);
                count.put(contingutDigital, count.get(contingutDigital) + 1);
            } else {
                result.put(contingutDigital, rating);
                count.put(contingutDigital, 1);
            }
        }
        result.replaceAll((t, v) -> result.get(t) / count.get(t));
        return result;
    }
}
