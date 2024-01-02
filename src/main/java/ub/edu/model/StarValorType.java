package ub.edu.model;

public class StarValorType extends ValorFactory {
    @Override
    public Valoracio createValoracio(float valoracio) {
        return new StarValue(valoracio);
    }
}
