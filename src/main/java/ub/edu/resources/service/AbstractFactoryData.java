package ub.edu.resources.service;

import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

public interface AbstractFactoryData {
    DAOClient createDAOClient();
    DAOEpisodi createDAOEpisodi();
    DAOTemporada createDAOTemporada();
    DAOPelicula createDAOPelicula();
    DAOSerie createDAOSerie();
    DAOTematica createDAOTematica();
    DAOComunitat createDAOComunitat();

    // Relacions
    DAORelacioTematicaPelicula createDAORelacioTematicaPelicula();
    DAORelacioTematicaSerie createDAORelacioTematicaSerie();
    DAORelacioClientComunitat createDAORelacioClientComunitat();
    DAORelacioTematicaComunitat createDAORelacioTematicaComunitat();

    // Valoracions
    DAORelacioClientPeliculaValoracioPunts createDAORelacioClientPeliculaValoracioPunts();
    DAORelacioClientPeliculaValoracioEstrelles createDAORelacioClientPeliculaValoracioEstrelles();
    DAORelacioClientPeliculaValoracioLikes createDAORelacioClientPeliculaValoracioLikes();

    DAORelacioClientEpisodiValoracioPunts createDAORelacioClientEpisodiValoracioPunts();
    DAORelacioClientEpisodiValoracioEstrelles createDAORelacioClientEpisodiValoracioEstrelles();
    DAORelacioClientEpisodiValoracioLikes createDAORelacioClientEpisodiValoracioLikes();

    // Wish
    DAORelacioClientWishPelicula createDAORelacioClientWishPelicula();
    DAORelacioClientWishSerie createDAORelacioClientWishSerie();
}
