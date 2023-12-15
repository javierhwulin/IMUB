package ub.edu.model.cataleg;

import java.util.ArrayList;

import java.util.List;


public class ContingutDigital {

    private String titol;
    private String descripcio;
    private int anyPrimeraEmissio;


    private List<Tematica> llistaTematiques;

     String idioma;
     int durada;
     String estrena;
    public ContingutDigital(String titol) {
        this.titol = titol;
        llistaTematiques = new ArrayList<Tematica>();

    }
    public ContingutDigital(String titol, int anyPrimeraEmissio) {
        this.titol = titol;
        this.anyPrimeraEmissio = anyPrimeraEmissio;
        llistaTematiques = new ArrayList<Tematica>();
    }

    public ContingutDigital(String titol,int anyPrimeraEmissio,  String descripcio) {
        this.titol = titol;
        this.descripcio = descripcio;
        this.anyPrimeraEmissio = anyPrimeraEmissio;
        llistaTematiques = new ArrayList<Tematica>();
    }

    public String getNom() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getAnyPrimeraEmissio() {
        return anyPrimeraEmissio;
    }

    public void setAnyPrimeraEmissio(int anyPrimeraEmissio) {
        this.anyPrimeraEmissio = anyPrimeraEmissio;
    }
    public void addTematica(Tematica t) {
        this.llistaTematiques.add(t);
    }

    public List<Tematica> getLlistaTematiques() {
        return llistaTematiques;
    }

    public void setLlistaTematiques(List<Tematica> llistaTematiques) {
        this.llistaTematiques = llistaTematiques;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getDurada() {
        return durada;
    }

    public String getAnyEstrena() {
        return estrena;
    }

    public Object getTitol() { return titol;}
}
