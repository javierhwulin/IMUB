package ub.edu.model;
import ub.edu.model.cataleg.*;


import java.util.*;

public class ModelFacade {
    private final imUBCataleg imubCataleg;
    private final imUBClients imubClients;
    private final ValorFacade valorFacade;
    public ModelFacade(imUBCataleg imub, imUBClients imubc) {
        this.imubCataleg = imub;
        this.imubClients = imubc;
        this.valorFacade = new ValorFacade(imubCataleg);
    }

    public StatusType loguejarSociStatus(String correu, String password) {
        Client persona = findPersonaByCorreu(correu);
        if (persona == null) {
            return StatusType.CORREU_INEXISTENT;
        }
        if (persona.getPwd().equals(password)) {
            return StatusType.LOGIN_CORRECTE;
        } else {
            return StatusType.CONTRASENYA_INCORRECTA;
        }
    }

    public List<HashMap<Object, Object>> getAllTematiques() {
        List<Tematica> l = imubCataleg.getAllTematiques();
        List<HashMap<Object, Object>> tematiquesDisponibles = new ArrayList<>();
        int i;
        i = 0;
        for (Tematica t : l) {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("id", i++);
            hashMap.put("nom", t.getNomTematica());
            tematiquesDisponibles.add(hashMap);
        }
        return tematiquesDisponibles;
    }


    public List<HashMap<Object, Object>> getAllComunitatsPerPersona(String correu) {
        // TO DO: Opcional Practica 4: Cal retornar les comunitats de les quals forma part la persona amb correu "correu"
        List<Comunitat> comunitats = new ArrayList<>(); // línia a substituir per trobar les comunitats de la persona amb correu "correu"
        ArrayList<HashMap<Object, Object>> comunitatsPerPersona = new ArrayList<>();
        for (Comunitat comunitat : comunitats) {
            HashMap<Object, Object> comunitatPerPersona = new HashMap<>();
            comunitatPerPersona.put("nom", comunitat.getNom());
            comunitatPerPersona.put("descripcio", comunitat.getDescripcio());
            comunitatsPerPersona.add(comunitatPerPersona);
        }
        return comunitatsPerPersona;
    }


    public Client findPersonaByCorreu(String correu) {
        CarteraClients xp = imubClients.getCarteraClients();
        return (xp.find(correu));
    }

    public void addComunitatClient(String correuPersona, String nomComunitat) {
        Client client = findPersonaByCorreu(correuPersona);
        Comunitat comunitat = imubCataleg.findComunitat(nomComunitat);
        // TO DO: Opcional Practica 4: Cal afegir la comunitat al client

    }

    public List<HashMap<Object, Object>> getAllContingutsDigitalsPerNom() {
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (ContingutDigital c : imubCataleg.getAllContingutsDigitals()) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }

        return contingutsDisponibles;
    }

    public List<HashMap<Object, Object>> getAllComunitatsPerNom() {
        List<HashMap<Object, Object>> comunitatsDisponibles = new ArrayList<>();
        for (Comunitat c : imubCataleg.getAllComunitats()) {
            HashMap<Object, Object> atributComunitat = new HashMap<>();
            String nom = c.getNom();
            atributComunitat.put("nom", nom);
            comunitatsDisponibles.add(atributComunitat);
        }
        return comunitatsDisponibles;
    }

    public List<HashMap<Object, Object>> getAllTemporadesDeSerie(String id) {
        List<HashMap<Object, Object>> temporadesDisponibles = new ArrayList<>();
        Serie s = imubCataleg.findSerie(id);
        for (Temporada t : s.getTemporades()) {
            HashMap<Object, Object> atributTemporada = new HashMap<>();
            atributTemporada.put("numTemporada", t.getNumTemporada());
            temporadesDisponibles.add(atributTemporada);
        }
        return temporadesDisponibles;
    }

    public HashMap<Object, Object> getDetallsSerie(String idContingutAudiovisual) {
        HashMap<Object, Object> atributsSerie = new HashMap<>();
        Serie s = imubCataleg.findSerie(idContingutAudiovisual);
        atributsSerie.put("imatge", s.getImatgeUrl());
        atributsSerie.put("nom", s.getNom());
        atributsSerie.put("descripcio", s.getDescripcio());
        atributsSerie.put("dataAnyPrimeraEmissio", s.getAnyPrimeraEmissio());
        atributsSerie.put("idioma", s.getIdioma());
        atributsSerie.put("durada", s.getDurada());

        return atributsSerie;
    }

    public HashMap<Object, Object> getDetallsPelicula(String idContingutAudiovisual) {
        HashMap<Object, Object> atributsPelicula = new HashMap<>();
        Pelicula p = imubCataleg.findPelicula(idContingutAudiovisual);
        atributsPelicula.put("imatge", p.getURL());
        atributsPelicula.put("nom", p.getNom());
        atributsPelicula.put("descripcio", p.getDescripcio());
        atributsPelicula.put("dataAnyPrimeraEmissio", p.getAnyPrimeraEmissio());
        atributsPelicula.put("idioma", p.getIdioma());
        atributsPelicula.put("durada", p.getDurada());

        return atributsPelicula;
    }

    public List<HashMap<Object, Object>> getAllEpisodis(String idSerie, int idTemporada) {
        List<HashMap<Object, Object>> episodisDisponibles = new ArrayList<>();
        Serie s = imubCataleg.findSerie(idSerie);
        Temporada t = s.findTemporada(idTemporada);
        for (Episodi e : t.getEpisodis()) {
            HashMap<Object, Object> atributEpisodi = new HashMap<>();
            atributEpisodi.put("numEpisodi", e.getNumEpisodi());
            episodisDisponibles.add(atributEpisodi);
        }
        return episodisDisponibles;
    }

    public HashMap<Object, Object> getEpisodiDetalls(String idContingutAudiovisual, Integer numTemporada, Integer numEpisodi) {
        HashMap<Object, Object> atributsEpisodi = new HashMap<>();
        Serie s = imubCataleg.findSerie(idContingutAudiovisual);
        Temporada t = s.findTemporada(numTemporada);
        Episodi e = t.findEpisodi(numEpisodi);
        atributsEpisodi.put("nom", e.getNom());
        atributsEpisodi.put("descripcio", e.getDescripcio());
        atributsEpisodi.put("durada", e.getDurada());
        atributsEpisodi.put("imatge", e.getUrl());
        atributsEpisodi.put("numTemporada", e.getNumTemporada());
        atributsEpisodi.put("numEpisodi", e.getNumEpisodi());
        atributsEpisodi.put("nomSerie", e.getNomSerie());
        atributsEpisodi.put("anyEstrena", e.getAnyEstrena());
        atributsEpisodi.put("url", e.getUrl());
        return atributsEpisodi;
    }

    public boolean esPelicula(String nomContingut) {
        return imubCataleg.esPelicula(nomContingut);
    }

    public boolean valorarContingut(String nomContingut, String correu, ValorType valortype, String valoracio) {
    // TODO: Pràctica 4: Cal valorar el contingut amb nom nomContingut amb la valoració valoracio (Like/Dislike, punts o estrelles) del client amb correu correu
        switch (valortype) {
            case VALORAR_PER_LIKES -> {
                if(valoracio.equals("Like") || valoracio.equals("1")) {
                    System.out.println("Model Facade: valorarContingut -> nomContingut: " + nomContingut + " correu: " + correu + " valortype: " + valortype + " valoracio: " + valoracio);
                    Client client = imubClients.getCarteraClients().find(correu);
                    return valorFacade.valorarContingut(client, valortype, nomContingut, 1);
                } else if(valoracio.equals("Dislike") || valoracio.equals("0")) {
                    System.out.println("Model Facade: valorarContingut -> nomContingut: " + nomContingut + " correu: " + correu + " valortype: " + valortype + " valoracio: " + valoracio);
                    Client client = imubClients.getCarteraClients().find(correu);
                    return valorFacade.valorarContingut(client, valortype, nomContingut, 0);
                } else {
                    System.out.println("Model Facade: valorarContingut -> valoració no vàlida");
                    return false;
                }
            }
            case VALORAR_PER_PUNTS, VALORAR_PER_ESTRELLES -> {
                System.out.println("Model Facade: valorarContingut -> nomContingut: " + nomContingut + " correu: " + correu + " valortype: " + valortype + " valoracio: " + valoracio);
                Client client = imubClients.getCarteraClients().find(correu);
                return valorFacade.valorarContingut(client, valortype, nomContingut, Float.parseFloat(valoracio));
            }
            default -> {
                System.out.println("Model Facade: valorarContingut -> tipus de valoració no vàlida");
                return false;
            }
        }
    }

    public boolean valorarContingut(String nomContingut, int numTemporada, int numEpisodi, String correu, ValorType valortype, String valoracio) {
        switch (valortype) {
            case VALORAR_PER_LIKES -> {
                if(valoracio.equals("Like") || valoracio.equals("1")) {
                    System.out.println("Model Facade: valorarContingut -> nomContingut: " + nomContingut + " correu: " + correu + " valortype: " + valortype + " valoracio: " + valoracio);
                    Client client = imubClients.getCarteraClients().find(correu);
                    return valorFacade.valorarContingut(client, valortype, nomContingut, numTemporada, numEpisodi, 1);
                } else if(valoracio.equals("Dislike") || valoracio.equals("0")) {
                    System.out.println("Model Facade: valorarContingut -> nomContingut: " + nomContingut + " correu: " + correu + " valortype: " + valortype + " valoracio: " + valoracio);
                    Client client = imubClients.getCarteraClients().find(correu);
                    return valorFacade.valorarContingut(client, valortype, nomContingut, numTemporada, numEpisodi, 0);
                } else {
                    System.out.println("Model Facade: valorarContingut -> valoració no vàlida");
                    return false;
                }
            }
            case VALORAR_PER_PUNTS, VALORAR_PER_ESTRELLES -> {
                System.out.println("Model Facade: valorarContingut -> nomContingut: " + nomContingut + " correu: " + correu + " valortype: " + valortype + " valoracio: " + valoracio);
                Client client = imubClients.getCarteraClients().find(correu);
                return valorFacade.valorarContingut(client, valortype, nomContingut, numTemporada, numEpisodi, Float.parseFloat(valoracio));
            }
            default -> {
                System.out.println("Model Facade: valorarContingut -> tipus de valoració no vàlida");
                return false;
            }
        }
    }
    public StatusType addToWishList(String nomContingut, String correu) {
        // TODO: Pràctica 4: Cal afegir el contingut nomContingut a la wishList del client amb correu correu
        System.out.println("Model Facade: addToWishList -> nomContingut: " + nomContingut + " correu: " + correu);
        imubClients.getCarteraClients().find(correu).addToWishList(imubCataleg.findContingutDigital(nomContingut));
        return StatusType.ADDED_TO_WISH_LIST;
    }

    public List<HashMap<Object, Object>> getWishList(String correu, int quantitat) {
        List<HashMap<Object, Object>> wishList = new ArrayList<>();
        // TODO: Pràctica 4: Cal retornar els continguts de la wishList del client amb correu correu
        System.out.println("Model Facade: getWishList -> correu: " + correu + " quantitat de wishes: " + quantitat);
        List<ContingutDigital> contingutDigitals = imubClients.getCarteraClients().find(correu).getWishList();
        if(contingutDigitals.isEmpty()) {
            System.out.println("Model Facade: getWishList -> no hi ha continguts");
        }
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (ContingutDigital c : contingutDigitals) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }

        return contingutsDisponibles;
    }

    public List<HashMap<String, String>> TopList(ContingutType tipusContingut, ValorType tipusValoracio, ValorType tipusCalcul, int top) throws IllegalArgumentException {
        // TO DO: Pràctica 4: Cal retornar els #top continguts de tipus tipusContingut amb la valoració tipusValoracio i el càlcul tipusCalcul

        System.out.println("Model Facade: TopList -> tipusContingut: " + tipusContingut + ", tipusValoracio: " + tipusValoracio + ", tipusCalcul: " + tipusCalcul + ", top: " + top);
        switch(tipusValoracio) {
            case VALORAR_PER_LIKES -> {
                return valorFacade.TopLikeList(tipusContingut, tipusCalcul, top);
            }
            case VALORAR_PER_PUNTS -> {
                return valorFacade.TopPointList(tipusContingut, tipusCalcul, top);
            }
            case VALORAR_PER_ESTRELLES -> {
                return valorFacade.TopStarList(tipusContingut, tipusCalcul, top);
            }
            default -> {
                System.out.println("Model Facade: TopList -> tipus de valoració no vàlida");
                return null;
            }
        }
    }

    public List<HashMap<Object, Object>> getAllPelicules() {
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (Pelicula c : imubCataleg.getAllPelicules()) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }

        return contingutsDisponibles;
    }

    public List<HashMap<Object, Object>> getAllSeries() {
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (Serie c : imubCataleg.getAllSeries()) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }

        return contingutsDisponibles;
    }

    public List<HashMap<Object, Object>> getAllContingutsDigitalsPerTematica(String tematica) {
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (ContingutDigital c : imubCataleg.getAllContingutsDigitalsPerTematica(tematica)) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }
        return contingutsDisponibles;
    }

    public List<HashMap<Object, Object>> getAllPeliculesPerTematica(String tematica) {
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (Pelicula c : imubCataleg.getAllPeliculesPerTematica(tematica)) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }
        return contingutsDisponibles;
    }

    public List<HashMap<Object, Object>> getAllSeriesPerTematica(String tematica) {
        List<HashMap<Object, Object>> contingutsDisponibles = new ArrayList<>();
        int id = 0;
        for (Serie c : imubCataleg.getAllSeriesPerTematica(tematica)) {
            HashMap<Object, Object> atributContingut = new HashMap<>();
            atributContingut.put("id", id++);
            String nom = c.getNom();
            atributContingut.put("nom", nom);
            contingutsDisponibles.add(atributContingut);
        }
        return contingutsDisponibles;
    }
}
