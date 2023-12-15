package ub.edu.model;

public class Valorar {
    private ValorarStrategy valorarStrategy;
    public void setValorarStrategy(ValorarStrategy valorarStrategy) {
        this.valorarStrategy = valorarStrategy;
    }
    public boolean executeValoracio(imUBClients imubClients, imUBCataleg cataleg, String nomContingut, String correu, int valoracio) {
        return valorarStrategy.executeValoracio(imubClients, cataleg, nomContingut, correu, valoracio);
    }
}
