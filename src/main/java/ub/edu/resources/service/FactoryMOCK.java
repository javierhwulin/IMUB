package ub.edu.resources.service;

import ub.edu.resources.dao.MOCK.entities.*;
import ub.edu.resources.dao.MOCK.relationships.DAORelacioClientComunitatMOCK;
import ub.edu.resources.dao.MOCK.relationships.DAORelacioTematicaComunitatMOCK;
import ub.edu.resources.dao.MOCK.relationships.DAORelacioTematicaPeliculaMOCK;
import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

public class FactoryMOCK implements AbstractFactoryData {

    @Override
    public DAOClient createDAOClient() {
        return new DAOClientMOCK();
    }

    @Override
    public DAOEpisodi createDAOEpisodi() {
        return new DAOEpisodiMOCK();
    }

    @Override
    public DAOTemporada createDAOTemporada() {
        return new DAOTemporadaMOCK();
    }

    @Override
    public DAOPelicula createDAOPelicula() {
        return new DAOPeliculaMOCK();
    }

    @Override
    public DAOSerie createDAOSerie() {
        return new DAOSerieMOCK();
    }

    @Override
    public DAOTematica createDAOTematica() {
        return new DAOTematicaMOCK();
    }

    @Override
    public DAOComunitat createDAOComunitat() {
        return new DAOComunitatMOCK();
    }

    @Override
    public DAORelacioTematicaPelicula createDAORelacioTematicaPelicula() {
        return new DAORelacioTematicaPeliculaMOCK();
    }
    @Override
    public DAORelacioClientComunitat createDAORelacioClientComunitat() {
        return new DAORelacioClientComunitatMOCK();
    }

    @Override
    public DAORelacioTematicaComunitat createDAORelacioTematicaComunitat() {
        return new DAORelacioTematicaComunitatMOCK();
    }

    @Override
    public DAORelacioTematicaSerie createDAORelacioTematicaSerie() {
        return null;
    }

    @Override
    public DAORelacioClientPeliculaValoracioPunts createDAORelacioClientPeliculaValoracioPunts () {
        return null;
    }

    @Override
    public DAORelacioClientPeliculaValoracioEstrelles createDAORelacioClientPeliculaValoracioEstrelles () {
        return null;
    }

    @Override
    public DAORelacioClientPeliculaValoracioLikes createDAORelacioClientPeliculaValoracioLikes () {
        return null;
    }

    @Override
    public DAORelacioClientEpisodiValoracioPunts createDAORelacioClientEpisodiValoracioPunts () {
        return null;
    }

    @Override
    public DAORelacioClientEpisodiValoracioEstrelles createDAORelacioClientEpisodiValoracioEstrelles () {
        return null;
    }


    public DAORelacioClientEpisodiValoracioLikes createDAORelacioClientEpisodiValoracioLikes () {
        return null;
    }

    // TO DO Pràctica 2:  Crear els altres DAOs de les altres classes
    public DAORelacioClientWishPelicula createDAORelacioClientWishPelicula() {
        return null;
    }
    public DAORelacioClientWishSerie createDAORelacioClientWishSerie() {
        return null;
    }
}
