package ub.edu.model;

import ub.edu.model.cataleg.*;

import java.util.ArrayList;
import java.util.List;

public class imUBCataleg {
    private List<Serie> llistaSeries;
    private List<Pelicula> llistaPelicules;
    private List<Tematica> llistaTematiques;
    private List<Comunitat> llistaComunitats;

    public imUBCataleg(){

        llistaSeries = new ArrayList<>();
        llistaPelicules = new ArrayList<>();
        llistaTematiques = new ArrayList<>();
        llistaComunitats = new ArrayList<>();
    }
    // Creador el ImUB

    public imUBCataleg(List<Serie> llistaSeries, List<Pelicula> llistaPelicules, List<Tematica> llistaTematiques, List<Comunitat> llistaComunitats) {
       
        this.llistaSeries = llistaSeries;
        this.llistaPelicules = llistaPelicules;
        this.llistaTematiques = llistaTematiques;
        this.llistaComunitats = llistaComunitats;
    }

    public void setLlistaSeries(List<Serie> llistaSeries) {
        this.llistaSeries = llistaSeries;
    }

    public void setLlistaPelicules(List<Pelicula> llistaPelicules) {
        this.llistaPelicules = llistaPelicules;
    }


    public void setLlistaTematiques(List<Tematica> llistaTematiques) {
        this.llistaTematiques = llistaTematiques;
    }


    public void setLlistaComunitats(List<Comunitat> llistaComunitats) {this.llistaComunitats = llistaComunitats;}

    public ContingutDigital findContingutDigital(String nomContingut){
        List<ContingutDigital> combinedList = new ArrayList<>(llistaPelicules);
        combinedList.addAll(llistaSeries);

        for (ContingutDigital p : combinedList) {
            if (p.getNom().equals(nomContingut)) {
                return p;
            }
        }
        return null;
    }

    public Tematica findTematica(String nomTematica) {
        for (Tematica t : llistaTematiques) {
            if (t.getNomTematica().equals(nomTematica)) {
                return t;
            }
        }
        return null;
    }
    public Pelicula findPelicula(String nomPeli) {
        for (Pelicula p : llistaPelicules) {
            if (p.getNom().equals(nomPeli)) {
                return p;
            }
        }
        return null;
    }

    public Serie findSerie(String nomSerie) {
        for (Serie s : llistaSeries) {
            if (s.getNom().equals(nomSerie)) {
                return s;
            }
        }
        return null;
    }

    public Comunitat findComunitat(String nomComunitat) {
        for (Comunitat s : llistaComunitats) {
            if (s.getNom().equals(nomComunitat)) {
                return s;
            }
        }
        return null;
    }

    public List<Tematica> getAllTematiques() {
        return llistaTematiques;
    }

    public List<Pelicula> getAllPelicules() {
        return llistaPelicules;
    }

    public List<Serie> getAllSeries() {
        return llistaSeries;
    }

    public List<Comunitat> getAllComunitats() {
        return llistaComunitats;
    }

    public List<ContingutDigital> getAllContingutsDigitals() {
        List<ContingutDigital> contingutDigitals = new ArrayList<ContingutDigital>();
        contingutDigitals.addAll(llistaPelicules);
        contingutDigitals.addAll(llistaSeries);
        return contingutDigitals;
    }

    public boolean esPelicula(String nomContingut) {
        for (Pelicula p : llistaPelicules) {
            if (p.getNom().equals(nomContingut)) {
                return true;
            }
        }
        return false;
    }

    public List<ContingutDigital> getAllContingutsDigitalsPerTematica(String tematica) {
        List<ContingutDigital> contingutDigitals = new ArrayList<>();
        for (ContingutDigital c : getAllContingutsDigitals()) {
            for(Tematica t : c.getLlistaTematiques()) {
                if (t.getNomTematica().equals(tematica)) {
                    contingutDigitals.add(c);
                }
            }
        }
        return contingutDigitals;
    }

    public List<Pelicula> getAllPeliculesPerTematica(String tematica) {
        List<Pelicula> pelicules = new ArrayList<>();
        for (Pelicula p : getAllPelicules()) {
            for(Tematica t : p.getLlistaTematiques()) {
                if (t.getNomTematica().equals(tematica)) {
                    pelicules.add(p);
                }
            }
        }
        return pelicules;
    }

    public List<Serie> getAllSeriesPerTematica(String tematica) {
        List<Serie> series = new ArrayList<>();
        for (Serie s : getAllSeries()) {
            for(Tematica t : s.getLlistaTematiques()) {
                if (t.getNomTematica().equals(tematica)) {
                    series.add(s);
                }
            }
        }
        return series;
    }
}
