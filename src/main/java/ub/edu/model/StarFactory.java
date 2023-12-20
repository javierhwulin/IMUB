package ub.edu.model;

public class StarFactory extends ValorFactory {
    @Override
    public Valoracio createValoracio(float valoracio) {
        return new StarValue(valoracio);
    }
}
