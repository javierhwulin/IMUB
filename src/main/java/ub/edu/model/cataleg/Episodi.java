package ub.edu.model.cataleg;

import java.util.ArrayList;
import java.util.List;

public class Episodi {
    // Episodi: nomSerie, numTemporada, numEpisodi, títolEpisodi, durada,
    // descripció, data d’estrena
    private String titol;
    private String nomSerie;
    private int numTemporada;
    private int numEpisodi;
    private int durada;
    private String descripcio;
    private String anyEstrena;
    private float valoracioInicial;
    private String url;
    private List<Tematica> llistaTematiques;

    public Episodi(String nomSerie, int numTemporada, int numEpisodi, String titolEpisodi, int durada) {
        this.titol= titolEpisodi;
        this.nomSerie = nomSerie;
        this.numTemporada = numTemporada;
        this.numEpisodi = numEpisodi;
        this.durada = durada;
        llistaTematiques = new ArrayList<Tematica>();
    }
    public Episodi(String nomSerie, int numTemporada, int numEpisodi, String titolEpisodi, int durada, float valoracioInicial) {
        this.titol = titolEpisodi;
        this.nomSerie = nomSerie;
        this.numTemporada = numTemporada;
        this.numEpisodi = numEpisodi;
        this.durada = durada;
        llistaTematiques = new ArrayList<Tematica>();
    }

    public Episodi(String nomSerie, int numTemporada, int numEpisodi, String titolEpisodi) {
        this.titol = titolEpisodi;
        this.nomSerie = nomSerie;
        this.numTemporada = numTemporada;
        this.numEpisodi = numEpisodi;

        llistaTematiques = new ArrayList<Tematica>();
    }

    public Episodi(String nomSerie, int numTemporada, int numEpisodi, String nom, String descripcio, String anyEstrena, String durada,  String url, float valoracio) {
        this.nomSerie = nomSerie;
        this.numTemporada = numTemporada;
        this.numEpisodi = numEpisodi;
        this.titol = nom;
        this.durada = Integer.parseInt(durada);
        this.descripcio = descripcio;
        this.anyEstrena = anyEstrena;
        this.valoracioInicial = valoracio;
        this.url = url;
        llistaTematiques = new ArrayList<Tematica>();
    }

    public String getNom() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getNumEpisodi() {
        return numEpisodi;
    }
    public String getNomSerie() {
        return nomSerie;
    }
    public int getNumTemporada() {
        return numTemporada;
    }
    public void addTematica(Tematica t) {
        this.llistaTematiques.add(t);
    }
    public List<Tematica> getLlistaTematiques() {
        return llistaTematiques;
    }

    public  boolean equals (Episodi p) {
        return (this.nomSerie.equals(p.getNomSerie()) && this.numTemporada == p.getNumTemporada() && this.numEpisodi == p.getNumEpisodi()) ;
    }

    public float getValoracioInicial() {
        return valoracioInicial;
    }

    public String getTitol() {
        return titol;
    }

    public int getDurada() {
        return durada;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getAnyEstrena() {
        return anyEstrena;
    }

    public String getUrl() {
        return url;
    }

    public void addValoracioInicial(float valoracioInicial) {
        this.valoracioInicial = valoracioInicial;
    }
}
