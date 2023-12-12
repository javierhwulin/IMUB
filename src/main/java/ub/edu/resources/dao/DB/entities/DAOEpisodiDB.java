package ub.edu.resources.dao.DB.entities;

import ub.edu.model.cataleg.Episodi;
import ub.edu.resources.dao.entities.DAOEpisodi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOEpisodiDB implements DAOEpisodi {

    private final Connection connection;

    public DAOEpisodiDB(Connection c){
        this.connection = c;
    }

    @Override
    public List<Episodi> getAll() throws Exception {
        List<Episodi> episodis = new ArrayList<>();
        String sql = """
            SELECT
                Serie.titol,
                Temporada.numTemporada,
                Episodi.numEpisodi,
                Episodi.nom,
                Episodi.descripcio,
                Episodi.anyEstrena,
                Episodi.duracio,
                Episodi.url,
                Episodi.valoracio
            FROM
                Episodi
                    LEFT JOIN Temporada ON Temporada.id = Episodi.temporada_id
                    LEFT JOIN Serie ON Serie.id = Temporada.serie_id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                episodis.add(
                    new Episodi(
                        rs.getString("titol"), rs.getInt("numTemporada"),
                        rs.getInt("numEpisodi"), rs.getString("nom"),
                        rs.getString("descripcio"), rs.getString("anyEstrena"),
                        rs.getString("duracio"),
                        rs.getString("url"),
                        rs.getFloat("valoracio")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return episodis;
    }

    @Override
    public Optional<Episodi> getById(String[] id) throws Exception {
        String nomSerie = Objects.requireNonNull(id[0], "Serie name cannot be null");
        Integer numTemporada = Integer.parseInt(Objects.requireNonNull(id[1], "Temporada number cannot be null"));
        Integer numEpisodi = Integer.parseInt(Objects.requireNonNull(id[2], "Episodi number cannot be null"));

        Episodi episodi = null;
        String sql = String.format("""
            SELECT
                Serie.titol,
                Temporada.numTemporada,
                Episodi.numEpisodi,
                Episodi.nom,
                Episodi.valoracio
            FROM
                Episodi
                    LEFT JOIN Temporada ON Temporada.id = Episodi.temporada_id
                    LEFT JOIN Serie ON Serie.id = Temporada.serie_id
            WHERE
                Episodi.numEpisodi = '%d' AND Temporada.numTemporada = '%d' AND Serie.titol = '%s'
        """, numEpisodi, numTemporada, nomSerie);
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                episodi = new Episodi(
                    rs.getString("titol"), rs.getInt("numTemporada"),
                    rs.getInt("numEpisodi"), rs.getString("nom"), rs.getInt("valoracio")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(episodi);
    }

    @Override
    public boolean add(final Episodi episodi) throws Exception {
        int temporada_id = 0;

        // query temporada_id
        String sql = String.format("""
            SELECT
                id
            FROM
                Temporada
                    LEFT JOIN Serie ON Temporada.serie_id = Serie.id
            WHERE
                Temporada.numTemporada = '%d' AND Serie.titol = '%s'
        """, episodi.getNumTemporada(), episodi.getNomSerie());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                temporada_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // insert
        sql = String.format(
            "INSERT INTO Episodi ('temporada_id', 'numEpisodi', 'nom', 'valoracio') VALUES ('%d', '%d', '%s', '%f');",
            temporada_id, episodi.getNumEpisodi(), episodi.getNom(), episodi.getValoracioInicial()
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
    public boolean update(Episodi episodi, String[] params) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Episodi episodi) throws Exception {
        int episodi_id = 0;
        String sql = String.format("""
            SELECT
                Episodi.id
            FROM
                Episodi
                    LEFT JOIN Temporada ON Temporada.id = Episodi.temporada_id
                    LEFT JOIN Serie ON Serie.id = Temporada.serie_id
            WHERE
                Episodi.numEpisodi = '%d' AND Temporada.numTemporada = '%d' AND Serie.titol = '%s'
        """, episodi.getNumEpisodi(), episodi.getNumTemporada(), episodi.getNomSerie());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                episodi_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if ( episodi_id <= 0 ) {
            return false;
        }

        // Delete found episodi_id
        sql = String.format(
            "DELETE FROM Episodi WHERE id='%d';", episodi_id
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
}
