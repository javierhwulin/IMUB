package ub.edu.model.cataleg;

import java.util.ArrayList;
import java.util.List;

public class Serie extends ContingutDigital {

    private String imatgeUrl;
    private float valoracioInicial;
    private List<Temporada> llistaTemporades;

    public Serie(String nomSerie, String idioma, int anyEstrena) {
        super(nomSerie, anyEstrena);
        llistaTemporades = new ArrayList<Temporada>();
    }


    public Serie(String nomSerie, int anyEstrena) {
        super(nomSerie, anyEstrena);
        llistaTemporades = new ArrayList<Temporada>();
    }

    public Serie(String nomSerie, String descripcio, String url, int anyEstrena, String idioma) {
        super(nomSerie, anyEstrena, descripcio);
        this.imatgeUrl = url;
        llistaTemporades = new ArrayList<Temporada>();
    }

    public Serie(String nomSerie, String descripcio, String url, int anyEstrena, String idioma, int durada) {
        super(nomSerie, anyEstrena, descripcio);
        llistaTemporades = new ArrayList<Temporada>();
        this.imatgeUrl = url;
    }

    public void addTemporada(Temporada temporada){
        llistaTemporades.add(temporada);
    }

    public List<Temporada> getTemporades() {
        return llistaTemporades;
    }


    public int getNumTemporades() {
        return llistaTemporades.size();
    }


    public Temporada findTemporada(int numTemporada) {
        boolean trobat = false;
        int i = 0;
        while (!trobat && i < llistaTemporades.size()) {
            if (llistaTemporades.get(i).getNumTemporada() == numTemporada) {
                trobat = true;
            } else i++;
        }
        if (trobat) return llistaTemporades.get(i);
        else return null;
    }
    public String getImatgeUrl() {
        return imatgeUrl;
    }

    public Float addValoracioInicial(float valoracio) {
        this.valoracioInicial = valoracio;
        return valoracioInicial;
    }

    public Float calculaValoracio() {
        int numValoracio = 0;
        float valoracioInicial = 0;

        for (Temporada t : llistaTemporades) {
            for (Episodi e : t.getEpisodis()) {
                valoracioInicial += e.getValoracioInicial();
                numValoracio++;
            }
        }
        return addValoracioInicial(valoracioInicial / numValoracio);
    }
}