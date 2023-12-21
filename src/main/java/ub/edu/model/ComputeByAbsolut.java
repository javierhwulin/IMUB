package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;
import java.util.Map;

public class ComputeByAbsolut implements ValorarStrategy{
    @Override
    public HashMap<ContingutDigital, Float> executeValoracio(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> allRatings) {
        HashMap<ContingutDigital, Float> ratings = new HashMap<>();
        for (Map.Entry<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> entry : allRatings.entrySet()) {
            Quartet<Client, ContingutDigital, Integer, Integer> key = entry.getKey();
            Valoracio valoracio = entry.getValue();
            ContingutDigital contingutDigital = key.getElement2();
            ratings.merge(contingutDigital, valoracio.getValoracio(), Float::sum);
        }
        return ratings;
    }
}
