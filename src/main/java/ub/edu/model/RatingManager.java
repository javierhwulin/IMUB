package ub.edu.model;

import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.*;
import java.util.HashMap;

public class RatingManager {
    public void addFilmRating(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, imUBCataleg cataleg, Client client, String title, float rating) {
        ContingutDigital contingutDigital = cataleg.findContingutDigital(title);
        if(contingutDigital == null){
            System.out.println("Model Facade: valorarContingut -> contingut no trobat");
        }else if(contingutDigital instanceof Pelicula) {
            ValorFactory valorFactory = new PointValorType();
            Pelicula pelicula = (Pelicula) contingutDigital;
            valorFactory.addValoracio(client, pelicula, valoracions, rating);
        } else{
            System.out.println("Model Facade: valorarContingut -> contingut no es una pelicula");
        }
    }
    public void addEpisodeRating(HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions, imUBCataleg cataleg, Client client, String title, int numTemporada, int numEpisodi, float rating) {
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
                    ValorFactory valorFactory = new PointValorType();
                    valorFactory.addValoracio(client, serie, valoracions, rating);
                }
            }
        }else{
            System.out.println("Model Facade: valorarContingut -> contingut no es una serie");
        }
    }

}
