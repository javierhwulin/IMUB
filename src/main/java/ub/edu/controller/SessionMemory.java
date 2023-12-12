package ub.edu.controller;

public class SessionMemory {
    String correuPersona;
    String nomComunitat;
    String nomObraAutiovisual;
    String nomPelicula;
    String nomSerie;
    Integer numTemporada;
    Integer numEpisodi;
    String nomTematica;

    String titolEpisodi;
    String tipusObra;
    String tipusValoracio;
    String tipusStrategy;

    public SessionMemory() {
    }

    public String getCorreuPersona() {
        return correuPersona;
    }

    public void setCorreuPersona(String correuPersona) {
        this.correuPersona = correuPersona;
    }

    public String getNomComunitat() {
        return nomComunitat;
    }

    public void setNomComunitat(String nomComunitat) {
        this.nomComunitat = nomComunitat;
    }

    public String getNomPelicula() {
        return nomPelicula;
    }

    public void setNomPelicula(String nomPelicula) {
        this.nomPelicula = nomPelicula;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    public Integer getNumTemporada() {
        return numTemporada;
    }

    public void setNumTemporada(Integer numTemporada) {
        this.numTemporada = numTemporada;
    }

    public Integer getNumEpisodi() {
        return numEpisodi;
    }

    public void setNumEpisodi(Integer numEpisodi) {
        this.numEpisodi = numEpisodi;
    }

    public String getNomTematica() {
        return nomTematica;
    }

    public void setNomTematica(String nomTematica) {
        this.nomTematica = nomTematica;
    }

    public String getTipusObra() {
        return tipusObra;
    }

    public void setTipusObra(String tipusObra) {
        this.tipusObra = tipusObra;
    }

    public String getTitolEpisodi() {
        return titolEpisodi;
    }

    public void setTitolEpisodi(String titolEpisodi) {
        this.titolEpisodi = titolEpisodi;
    }

    public void setNomObra(String nom) {
        nomObraAutiovisual = nom;
    }
    public String getNomObra() {
        return nomObraAutiovisual;
    }

    public String getTipusStrategy() {
        return tipusStrategy;
    }

    public void setTipusStrategy(String tipusStrategy) {
        this.tipusStrategy = tipusStrategy;
    }

    public String getTipusValoracio() {
        return tipusValoracio;
    }

    public void setTipusValoracio(String tipusValoracio) {
        this.tipusValoracio = tipusValoracio;
    }
}
