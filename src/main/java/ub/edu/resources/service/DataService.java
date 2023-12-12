package ub.edu.resources.service;

import ub.edu.model.*;
import ub.edu.model.ED.*;
import ub.edu.model.cataleg.*;
import ub.edu.resources.dao.*;
import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

import java.util.List;
import java.util.Optional;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DataService {
    private DAOComunitat daoComunitat;
    private DAOEpisodi daoEpisodi;
    private DAOPelicula daoPelicula;
    private DAOClient daoClient;
    private DAOSerie daoSerie;
    private DAOTematica daoTematica;
    private DAOTemporada daoTemporada;

    private DAORelacioClientComunitat daoRelacioClientComunitat;
    private DAORelacioTematicaPelicula daoRelacioTematicaPelicula;
    private DAORelacioTematicaSerie daoRelacioTematicaSerie;
    private DAORelacioTematicaComunitat daoRelacioTematicaComunitat;

    // Valoracions
    private DAORelacioClientPeliculaValoracioPunts daoRelacioClientPeliculaValoracioPunts;
    private DAORelacioClientPeliculaValoracioEstrelles daoRelacioClientPeliculaValoracioEstrelles;
    private DAORelacioClientPeliculaValoracioLikes daoRelacioClientPeliculaValoracioLikes;

    private DAORelacioClientEpisodiValoracioPunts daoRelacioClientEpisodiValoracioPunts;
    private DAORelacioClientEpisodiValoracioEstrelles daoRelacioClientEpisodiValoracioEstrelles;
    private DAORelacioClientEpisodiValoracioLikes daoRelacioClientEpisodiValoracioLikes;

    // Wish
    private DAORelacioClientWishPelicula daoRelacioClientWishPelicula;
    private DAORelacioClientWishSerie daoRelacioClientWishSerie;


    public DataService(AbstractFactoryData factory) {
        this.daoComunitat = factory.createDAOComunitat();
        this.daoEpisodi = factory.createDAOEpisodi();
        this.daoPelicula = factory.createDAOPelicula();
        this.daoClient = factory.createDAOClient();
        this.daoSerie = factory.createDAOSerie();
        this.daoTematica = factory.createDAOTematica();
        this.daoTemporada = factory.createDAOTemporada();

        // Relacions
        this.daoRelacioClientComunitat = factory.createDAORelacioClientComunitat();
        this.daoRelacioTematicaPelicula = factory.createDAORelacioTematicaPelicula();
        this.daoRelacioTematicaSerie = factory.createDAORelacioTematicaSerie();
        this.daoRelacioTematicaComunitat = factory.createDAORelacioTematicaComunitat();

        // Relacions valoracions - client - pelicula
        this.daoRelacioClientPeliculaValoracioPunts = factory.createDAORelacioClientPeliculaValoracioPunts();
        this.daoRelacioClientPeliculaValoracioEstrelles = factory.createDAORelacioClientPeliculaValoracioEstrelles();
        this.daoRelacioClientPeliculaValoracioLikes = factory.createDAORelacioClientPeliculaValoracioLikes();

        // Relacions valoracions - client - episodi
        this.daoRelacioClientEpisodiValoracioPunts = factory.createDAORelacioClientEpisodiValoracioPunts();
        this.daoRelacioClientEpisodiValoracioEstrelles = factory.createDAORelacioClientEpisodiValoracioEstrelles();
        this.daoRelacioClientEpisodiValoracioLikes = factory.createDAORelacioClientEpisodiValoracioLikes();

        // Wish
        this.daoRelacioClientWishPelicula = factory.createDAORelacioClientWishPelicula();
        this.daoRelacioClientWishSerie = factory.createDAORelacioClientWishSerie();
    }


    public List<Pelicula> getAllPelicules() throws Exception {
        List<Pelicula> pelicules = daoPelicula.getAll();
        List<Tematica> tematiques = daoTematica.getAll();
        List<Parell<String, String>> relacions = daoRelacioTematicaPelicula.getAll();
        for (Parell<String, String> relacio: relacions){
            for (Pelicula pelicula: pelicules) {
                if (pelicula.getNom().equals(relacio.getElement1())) {
                    for (Tematica tematica: tematiques) {
                        if (tematica.getNomTematica().equals(relacio.getElement2())) {
                            pelicula.addTematica(tematica);
                        }
                    }
                }
            }
        }

        return pelicules;
    }

    public List<Tematica> getAllTematiques() throws Exception {
        return daoTematica.getAll();
    }

    public List<Client> getAllPersones() throws Exception {
        return daoClient.getAll();
    }

    public List<Serie> getAllSeries() throws Exception {
        List<Serie> series = daoSerie.getAll();
        List<Temporada> temporades = daoTemporada.getAll();
        List<Episodi> episodis = daoEpisodi.getAll();
        for (Temporada temporada: temporades) {
            for (Episodi episodi: episodis) {
                if (episodi.getNomSerie().equals(temporada.getNomSerie()) && episodi.getNumTemporada() == (temporada.getNumTemporada())) {
                    temporada.addEpisodi(episodi);
                }
            }
            for (Serie serie: series) {
                if (serie.getNom().equals(temporada.getNomSerie())) {
                    serie.addTemporada(temporada);
                }
            }
        }
        return series;
    }

    public List<Temporada> getAllTemporades() throws Exception {
        List<Temporada> temporades = daoTemporada.getAll();
        List<Episodi> episodis = daoEpisodi.getAll();
        for (Temporada temporada: temporades) {
            for (Episodi episodi: episodis) {
                if (episodi.getNumTemporada() == temporada.getNumTemporada()) {
                    temporada.addEpisodi(episodi);
                }
            }
        }
        return temporades;
    }

    public Optional<Client> getClientByUsername(String usuari) {
        try {
            return daoClient.getById(new String[]{usuari});
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Parell<String, String>> getAllRelacionsPeliculesTematiques() {
        try {
            return daoRelacioTematicaPelicula.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Parell<String, String>> getAllRelacionsSeriesTematiques() {
        try {
            return daoRelacioTematicaSerie.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Parell<String, String>> getAllRelacionsTematicaComunitat() {
        try {
            return daoRelacioTematicaComunitat.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Comunitat> getAllComunitats() throws Exception {
        return  daoComunitat.getAll();
    }

    public List<Parell<String, String>> getAllRelacionsClientPeliculaWish() {
        try {
            return daoRelacioClientWishPelicula.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Parell<String, String>> getAllRelacionsClientSerieWish() {
        try {
            return daoRelacioClientWishSerie.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Quintet<String, String, Integer, Integer, Integer>> getAllRelacionsClientEpisodiEstrelles() {
        try {
            return daoRelacioClientEpisodiValoracioEstrelles.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Quintet<String, String, Integer, Integer, Float>> getAllRelacionsClientEpisodiPunts() {
        try {
            return daoRelacioClientEpisodiValoracioPunts.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Quintet<String, String, Integer, Integer, Boolean>> getAllRelacionsClientEpisodiLikes() {
        try {
            return daoRelacioClientEpisodiValoracioLikes.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Trio<String, String, Integer>> getAllRelacionsClientPeliculaEstrelles() {
        try {
            return daoRelacioClientPeliculaValoracioEstrelles.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Trio<String, String, Float>> getAllRelacionsClientPeliculaPunts() {
        try {
            return daoRelacioClientPeliculaValoracioPunts.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Trio<String, String, Boolean>> getAllRelacionsClientPeliculaLikes() {
        try {
            return daoRelacioClientPeliculaValoracioLikes.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addClient(Client client) {
        try {
            daoClient.add(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addRelacioClientComunitat(Parell<String, String> cp) {
        try {
            daoRelacioClientComunitat.add(cp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
