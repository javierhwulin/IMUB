package ub.edu.resources.dao.DB.entities;

import ub.edu.model.cataleg.Temporada;
import ub.edu.resources.dao.entities.DAOTemporada;
import ub.edu.model.ED.Parell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOTemporadaDB implements DAOTemporada {

    // Utilitzem nomSerie, numTemporada per identificar una temporada
    private final Connection connection;

    public DAOTemporadaDB(Connection c){
        this.connection = c;
    }

    @Override
    public Optional<Temporada> getById(String[] id) throws Exception {
        String nomSerie = Objects.requireNonNull(id[0], "Serie name cannot be null");
        int numTemporada = Integer.parseInt(Objects.requireNonNull(id[1], "Temporada number cannot be null"));
        return this.getById(nomSerie, numTemporada);
    }

    public Optional<Temporada> getById(String nomSerie, int numTemporada) throws Exception {
        Temporada temporada = null;
        String sql = String.format("""
            SELECT
                Serie.titol, Temporada.numTemporada
            FROM
                Temporada
                    LEFT JOIN Serie ON Temporada.serie_id = Serie.id
            WHERE
                Temporada.numTemporada = '%d' AND Serie.titol = '%s'
        """, numTemporada, nomSerie);
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                temporada = new Temporada(
                    rs.getString("titol"),
                    rs.getInt("numTemporada")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(temporada);
    }

    @Override
    public List<Temporada> getAll() throws Exception {
        List<Temporada> temporades = new ArrayList<>();
        String sql = """
            SELECT
                Serie.titol, Temporada.numTemporada
            FROM
                Temporada
                    LEFT JOIN Serie ON Temporada.serie_id = Serie.id
        """;
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                temporades.add(new Temporada(
                    rs.getString("titol"),
                    rs.getInt("numTemporada")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temporades;
    }

    @Override
    public boolean add(final Temporada temporada) throws Exception {
        int serie_id = 0;

        // query serie_id
        String sql = String.format("""
            SELECT id FROM Serie WHERE titol='%s';
        """, temporada.getNomSerie());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                serie_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // insert
        sql = String.format(
            "INSERT INTO Temporada ('serie_id', 'numTemporada') VALUES ('%s', '%s');",
            serie_id, temporada.getNumTemporada()
        );
        try (
            PreparedStatement statement = this.connection.prepareStatement(sql);
        ){
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Temporada temporada, String[] params) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Temporada temporada) throws Exception {
        return false;
    }
}
