package ub.edu.model;

public class LikeValorType extends ValorFactory {
    @Override
    public Valoracio createValoracio(float valoracio) {
        return new LikeValor(valoracio);
    }
}
