package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;

public interface ValorarStrategy {
    //Calculate the rating of a film or serie by the average of the ratings of the episodes or the film itself or the absolute of the ratings.
    HashMap<ContingutDigital, Float> executeValoracio(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> allRatings);
}
