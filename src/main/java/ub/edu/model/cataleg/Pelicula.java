package ub.edu.model.cataleg;
import ub.edu.model.cataleg.ContingutDigital;

public class Pelicula extends ContingutDigital {

    private String url;

    private float valoracioInicial;
    public Pelicula(String titol, int estrena, int durada) {
        super(titol, estrena);
        this.durada = durada;
    }

    public Pelicula(String titol, String descripcio, String url, int estrena, String idioma, int durada) {
        super(titol, estrena, descripcio);
        this.url = url;
        this.idioma = idioma;
        this.durada = durada;

    }

    public Pelicula(String titol, String descripcio, String url, int estrena, String idioma, float valoracio) {
        super(titol, estrena, descripcio);
        this.url = url;
        this.idioma = idioma;
        this.setValoracioInicial(valoracio);
    }

    public  boolean equals (Pelicula p) {
        return this.getTitol().equals(p.getNom());
    }

    public String getURL() {
        return url;
    }

    public float getValoracioInicial() {
        return valoracioInicial;
    }

    public void setValoracioInicial(float valoracioInicial) {
        this.valoracioInicial = valoracioInicial;
    }


}
