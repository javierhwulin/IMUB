package ub.edu.controller;

import ub.edu.model.*;
import ub.edu.resources.ResourcesFacade;
import ub.edu.view.ViewMemory;

import java.util.*;


public class Controller  {

    private final ResourcesFacade inicialitzador;

    private final ModelFacade modelFacade;

    private final imUBCataleg imubCataleg;

    private final imUBClients imubClients;

    private final SessionMemory sessionMemory;

    private final ViewMemory viewMemory;

    public Controller() {
        imubCataleg = new imUBCataleg();
        imubClients = new imUBClients();

        sessionMemory = new SessionMemory();
        viewMemory = new ViewMemory();
        modelFacade = new ModelFacade(imubCataleg, imubClients);
        inicialitzador = new ResourcesFacade(imubCataleg, imubClients, modelFacade);
        inicialitzador.populateImUB();
        inicialitzador.populateiImUBClients();
    }

    public SessionMemory getSessionMemory() {
        return sessionMemory;
    }

    public ViewMemory getViewMemory() {
        return viewMemory;
    }

    public String addNewPersona(String correu, String nom, String cognoms, String dni, String password) throws Exception {
        return (inicialitzador.addNewPersona(correu, nom, cognoms, dni, password).toString());
    }

    public String addRelacioComunitatClient(String correu, String comunitat) throws Exception {
        return inicialitzador.addRelacioClientComunitat(correu, comunitat).toString();

    }
    public String loguejarSociStatus(String correu, String password){
        return (modelFacade.loguejarSociStatus(correu, password).toString());
    }


    public List<HashMap<Object,Object>> getAllComunitatsPerPersona(String correu) throws Exception {
        return modelFacade.getAllComunitatsPerPersona(correu);
    }

    public List<HashMap<Object, Object>> getAllTematiques() throws Exception {
        return modelFacade.getAllTematiques();
    }

    public List<HashMap<Object, Object>> getAllContingutsDigitalsPerNom() {
        return modelFacade.getAllContingutsDigitalsPerNom();
    }

    public List<HashMap<Object, Object>> getAllComunitatsPerNom() {
        return modelFacade.getAllComunitatsPerNom();
    }

    public List<HashMap<Object, Object>> getAllTemporadesDeSerie(String id) {
        return modelFacade.getAllTemporadesDeSerie(id);
    }

    public List<HashMap<Object, Object>> getAllEpisodis(String idSerie, int idTemporada) {
        return modelFacade.getAllEpisodis(idSerie, idTemporada);
    }

    public HashMap<Object, Object> getDetallsSerie(String idContingutAudiovisual) {
        return modelFacade.getDetallsSerie(idContingutAudiovisual);
    }

    public HashMap<Object, Object> getDetallsPelicula(String idContingutAudiovisual) {
        return modelFacade.getDetallsPelicula(idContingutAudiovisual);
    }

    public HashMap<Object, Object> getEpisodiDetalls(String idContingutAudiovisual, Integer numTemporada, Integer numEpisodi) {
        return modelFacade.getEpisodiDetalls(idContingutAudiovisual, numTemporada, numEpisodi);
    }

    public boolean esPelicula(String nomContingut) {
        return modelFacade.esPelicula(nomContingut);
    }

    public boolean valorarContingut(String nomContingut, String correu, String valortype, String valoracio) {
        // TO DO Practica 4 : afegir les valoracions de pel·lícules
        return modelFacade.valorarContingut(nomContingut, correu, valortype, valoracio);
    }
    public String addToWishList(String nomContingut, String correu) {
        return modelFacade.addToWishList(nomContingut, correu).toString();
    }

    public List<HashMap<Object, Object>> getWishList(String correu) {
        return modelFacade.getWishList(correu, 10);
    }
    public List<HashMap<String, String>> top10(String tipusContingut, String tipusValoracio, String tipusCalcul) throws IllegalArgumentException{
        return modelFacade.TopList(tipusContingut, tipusValoracio, tipusCalcul, 10);
    }

    public List<HashMap<Object, Object>> getAllPelicules() {
        return modelFacade.getAllPelicules();
    }

    public List<HashMap<Object, Object>> getAllSeries() {
        return modelFacade.getAllSeries();
    }

    public List<HashMap<Object, Object>> getAllContingutsDigitalsPerTematica(String tematica) {
        return modelFacade.getAllContingutsDigitalsPerTematica(tematica);
    }

    public List<HashMap<Object, Object>> getAllPeliculesPerTematica(String tematica) {
        return modelFacade.getAllPeliculesPerTematica(tematica);
    }

    public List<HashMap<Object, Object>> getAllSeriesPerTematica(String tematica) {
        return modelFacade.getAllSeriesPerTematica(tematica);
    }
}
