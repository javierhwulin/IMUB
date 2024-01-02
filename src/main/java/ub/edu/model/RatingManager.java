package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;
import java.util.HashMap;

public class RatingManager {
    public void addFilmRating(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, imUBCataleg cataleg, Client client, String title, float rating, String typeValoracio) {
        ContingutDigital contingutDigital = cataleg.findContingutDigital(title);
        if(contingutDigital == null){
            System.out.println("Model Facade: valorarContingut -> contingut no trobat");
        }else if(contingutDigital instanceof Pelicula) {
            Pelicula pelicula = (Pelicula) contingutDigital;
            if(typeValoracio.equals(ValorType.VALORAR_PER_PUNTS.toString())) {
                ValorFactory valorFactory = new PointValorType();
                valorFactory.addValoracio(client, pelicula, valoracions, rating);
            }else if(typeValoracio.equals(ValorType.VALORAR_PER_ESTRELLES.toString())) {
                ValorFactory valorFactory = new StarValorType();
                valorFactory.addValoracio(client, pelicula, valoracions, rating);
            }else if((typeValoracio.equals(ValorType.VALORAR_PER_LIKES.toString()))) {
                ValorFactory valorFactory = new LikeValorType();
                valorFactory.addValoracio(client, pelicula, valoracions, rating);
            }else{
                System.out.println("Model Facade: valorarContingut -> typeValoracio no valida");
            }
        } else{
            System.out.println("Model Facade: valorarContingut -> contingut no es una pelicula");
        }
    }
    public void addEpisodeRating(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, imUBCataleg cataleg, Client client, String title, int numTemporada, int numEpisodi, float rating, String typeValoracio) {
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
                    if(typeValoracio.equals(ValorType.VALORAR_PER_PUNTS.toString())) {
                        ValorFactory valorFactory = new PointValorType();
                        valorFactory.addValoracio(client, serie, numTemporada, numEpisodi, valoracions, rating);
                    }else if(typeValoracio.equals(ValorType.VALORAR_PER_ESTRELLES.toString())) {
                        ValorFactory valorFactory = new StarValorType();
                        valorFactory.addValoracio(client, serie, numTemporada, numEpisodi, valoracions, rating);
                    }else if((typeValoracio.equals(ValorType.VALORAR_PER_LIKES.toString()))) {
                        ValorFactory valorFactory = new LikeValorType();
                        valorFactory.addValoracio(client, serie, numTemporada, numEpisodi, valoracions, rating);
                    }else{
                        System.out.println("Model Facade: valorarContingut -> typeValoracio no valida");
                    }
                }
            }
        }else{
            System.out.println("Model Facade: valorarContingut -> contingut no es una serie");
        }
    }

}
