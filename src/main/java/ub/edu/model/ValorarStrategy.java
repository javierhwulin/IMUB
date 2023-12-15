package ub.edu.model;

public interface ValorarStrategy {
    boolean executeValoracio(imUBClients imubClients, imUBCataleg cataleg, String nomContingut, String correu, int valoracio);
}
