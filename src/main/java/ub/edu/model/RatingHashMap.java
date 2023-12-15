package ub.edu.model;
import ub.edu.model.cataleg.*;

import java.util.HashMap;

public abstract class RatingHashMap{
    private final HashMap<Pelicula, Float> valoracionsPelis;
    private final HashMap<Serie, Float> valoracionsSerie; //RELACIONAR SERIE-TEMPORADA-EPISODI
    public RatingHashMap() {
        this.valoracionsPelis = new HashMap<>();
        this.valoracionsSerie = new HashMap<>();
    }

    public void addFilmRating(imUBCataleg cataleg, String title, float rating) {
        ContingutDigital contingutDigital = cataleg.findContingutDigital(title);
        if(contingutDigital == null){
            System.out.println("Model Facade: valorarContingut -> contingut no trobat");
        }else {
            Pelicula pelicula = (Pelicula) contingutDigital;
            pelicula.setValoracioInicial(rating);
            valoracionsPelis.put(pelicula, pelicula.getValoracioInicial());
        }
    }

    public void addEpisodeRating(imUBCataleg cataleg, String title, int numTemporada, int numEpisodi, float rating) {
        ContingutDigital contingutDigital = cataleg.findContingutDigital(title);
        if(contingutDigital == null) {
            System.out.println("Model Facade: valorarContingut -> contingut no trobat");
        }else if(contingutDigital instanceof Serie){
            Serie serie = (Serie) contingutDigital;
            Temporada temporada = serie.findTemporada(numTemporada);
            if(temporada == null) {
                System.out.println("Model Facade: valorarContingut -> temporada no trobat");
            }else {
                Episodi episodi = temporada.findEpisodi(numEpisodi);
                if (episodi == null) {
                    System.out.println("Model Facade: valorarContingut -> episodi no trobat");
                } else {
                    episodi.addValoracioInicial(rating);
                    valoracionsSerie.put(serie, rating);
                }
            }
        }else{
            System.out.println("Model Facade: valorarContingut -> contingut no es una serie");
        }
    }

    public HashMap<String, Float> getAllFilmRatings() {
        HashMap<String, Float> allRatings = new HashMap<>();
        for (Pelicula p : valoracionsPelis.keySet()) {
            allRatings.put(p.getNom(), valoracionsPelis.get(p));
        }
        return allRatings;
    }
    public HashMap<String, Float> getAllEpisodeRatings() {
        HashMap<String, Float> allRatings = new HashMap<>();
        for (Serie s : valoracionsSerie.keySet()) {
            allRatings.put(s.getNom(), valoracionsSerie.get(s));
        }
        return allRatings;
    }
}
