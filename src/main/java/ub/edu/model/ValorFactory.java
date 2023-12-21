package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;

import java.util.HashMap;

public abstract class ValorFactory {
    public abstract Valoracio createValoracio(float valoracio);

    public void addValoracio(Client client, ContingutDigital contingutDigital, HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, float valoracio){
        Valoracio valor = createValoracio(valoracio);
        valor.addValoracio(client, contingutDigital, valoracions);
    }
    public void addValoracio(Client client, ContingutDigital contingutDigital, int numTemporada, int numEpisodi, HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, float valoracio){
        Valoracio valor = createValoracio(valoracio);
        valor.addValoracio(client, contingutDigital, numTemporada, numEpisodi, valoracions);
    }
}
