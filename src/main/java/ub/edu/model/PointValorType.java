package ub.edu.model;

public class PointValorType extends ValorFactory {
    @Override
    public Valoracio createValoracio(float valoracio) {
        return new PointValor(valoracio);
    }
}
