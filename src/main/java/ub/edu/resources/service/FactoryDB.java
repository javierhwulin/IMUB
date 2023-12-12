package ub.edu.resources.service;

import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

import ub.edu.resources.dao.DB.entities.*;
import ub.edu.resources.dao.DB.relationships.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class FactoryDB implements AbstractFactoryData {

    private static final String DB_FILE_PATH = "./src/main/java/ub/edu/resources/dao/DB/data/imub.sqlite3";

    Connection connection;

    public FactoryDB() {
        this.connection = connect();
    }

    Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_PATH);
            c.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Connected to db successfully");
        return c;
    }

    void disconnect() {
        try {
            this.connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Closed database successfully");
        return;
    }

    @Override
    public DAOClient createDAOClient() {
        return new DAOClientDB(this.connection);
    }

    @Override
    public DAOComunitat createDAOComunitat() {
        return new DAOComunitatDB(this.connection);
    }

    @Override
    public DAOEpisodi createDAOEpisodi() {
        return new DAOEpisodiDB(this.connection);
    }

    @Override
    public DAOPelicula createDAOPelicula() {
        return new DAOPeliculaDB(this.connection);
    }

    @Override
    public DAOSerie createDAOSerie() {
        return new DAOSerieDB(this.connection);
    }

    @Override
    public DAOTematica createDAOTematica() {
        return new DAOTematicaDB(this.connection);
    }

    @Override
    public DAOTemporada createDAOTemporada() {
        return new DAOTemporadaDB(this.connection);
    }

    // Relationships

    @Override
    public DAORelacioClientComunitat createDAORelacioClientComunitat() {
        return new DAORelacioClientComunitatDB(this.connection);
    }

    @Override
    public DAORelacioTematicaComunitat createDAORelacioTematicaComunitat() {
        return new DAORelacioTematicaComunitatDB(this.connection);
    }

    @Override
    public DAORelacioTematicaPelicula createDAORelacioTematicaPelicula() {
        return new DAORelacioTematicaPeliculaDB(this.connection);
    }

    @Override
    public DAORelacioTematicaSerie createDAORelacioTematicaSerie() {
        return new DAORelacioTematicaSerieDB(this.connection);
    }

    // Valoracions
    @Override
    public DAORelacioClientPeliculaValoracioPunts createDAORelacioClientPeliculaValoracioPunts () {
        return new DAORelacioClientPeliculaValoracioPuntsDB(this.connection);
    }

    @Override
    public DAORelacioClientPeliculaValoracioEstrelles createDAORelacioClientPeliculaValoracioEstrelles () {
        return new DAORelacioClientPeliculaValoracioEstrellesDB(this.connection);
    }

    @Override
    public DAORelacioClientPeliculaValoracioLikes createDAORelacioClientPeliculaValoracioLikes () {
        return new DAORelacioClientPeliculaValoracioLikesDB(this.connection);
    }

    @Override
    public DAORelacioClientEpisodiValoracioPunts createDAORelacioClientEpisodiValoracioPunts () {
        return new DAORelacioClientEpisodiValoracioPuntsDB(this.connection);
    }

    @Override
    public DAORelacioClientEpisodiValoracioEstrelles createDAORelacioClientEpisodiValoracioEstrelles () {
        return new DAORelacioClientEpisodiValoracioEstrellesDB(this.connection);
    }

    @Override
    public DAORelacioClientEpisodiValoracioLikes createDAORelacioClientEpisodiValoracioLikes () {
        return new DAORelacioClientEpisodiValoracioLikesDB(this.connection);
    }

    // Wish
    @Override
    public DAORelacioClientWishPelicula createDAORelacioClientWishPelicula () {
        return new DAORelacioClientWishPeliculaDB(this.connection);
    }

    @Override
    public DAORelacioClientWishSerie createDAORelacioClientWishSerie () {
        return new DAORelacioClientWishSerieDB(this.connection);
    }

}
