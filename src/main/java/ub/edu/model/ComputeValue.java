package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;

public class ComputeValue {
    // Create a HashMap to store a String representing ContingutDigital and a float value representing the rating which is computed by the strategy.
    private ValorarStrategy valorarStrategy;
    public void setValorarStrategy(ValorarStrategy valorarStrategy) {
        this.valorarStrategy = valorarStrategy;
    }
    public HashMap<ContingutDigital, Float> executeValoracio(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio>  list) {
        return valorarStrategy.executeValoracio(list);
    }
}
