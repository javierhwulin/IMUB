package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;

public class PointValor implements Valoracio {
    private final float valoracio;

    public PointValor(float valoracio) {
        if(valoracio >= 0.0f && valoracio <= 10.0f){
            this.valoracio = valoracio;
        }else {
            throw new IllegalArgumentException("Valor no valida");
        }
    }

    public float getValoracio() {
        return valoracio;
    }

    @Override
    public void addValoracio(Client client, ContingutDigital contingutDigital, HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions) {
        valoracions.put(new Quartet<>(client, contingutDigital, 1, 1), this);
    }

    @Override
    public void addValoracio(Client client, ContingutDigital contingutDigital, int numTemporada, int numEpisodi, HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions) {
        valoracions.put(new Quartet<>(client, contingutDigital, numTemporada, numEpisodi), this);
    }
}
