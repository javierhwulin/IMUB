package ub.edu.model.cataleg;

import java.util.ArrayList;
import java.util.List;

public class Serie extends ContingutDigital {

    private String imatgeUrl;
    private final List<Temporada> llistaTemporades;

    public Serie(String nomSerie, String idioma, int anyEstrena) {
        super(nomSerie, anyEstrena);
        llistaTemporades = new ArrayList<>();
    }


    public Serie(String nomSerie, int anyEstrena) {
        super(nomSerie, anyEstrena);
        llistaTemporades = new ArrayList<>();
    }

    public Serie(String nomSerie, String descripcio, String url, int anyEstrena, String idioma) {
        super(nomSerie, anyEstrena, descripcio);
        this.imatgeUrl = url;
        llistaTemporades = new ArrayList<>();
    }

    public Serie(String nomSerie, String descripcio, String url, int anyEstrena, String idioma, int durada) {
        super(nomSerie, anyEstrena, descripcio);
        llistaTemporades = new ArrayList<>();
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
}