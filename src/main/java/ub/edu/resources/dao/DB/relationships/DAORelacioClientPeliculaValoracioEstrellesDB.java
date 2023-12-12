package ub.edu.resources.dao.DB.relationships;

import ub.edu.model.ED.Trio;
import ub.edu.resources.dao.relationships.DAORelacioClientPeliculaValoracioEstrelles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DAORelacioClientPeliculaValoracioEstrellesDB extends DAORelacioDB<Trio<String, String, Integer>> implements DAORelacioClientPeliculaValoracioEstrelles {

    private final Connection connection;

    public DAORelacioClientPeliculaValoracioEstrellesDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Trio<String, String, Integer>> getAll() {
        List<Trio<String, String, Integer>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Client.correuElectronic,
                Pelicula.titol,
                ValoracioEstrelles.puntuacio
            FROM
                Pelicula_ValoracioEstrelles AS Relacio
                    LEFT JOIN Client ON Relacio.client_id = Client.id
                    LEFT JOIN Pelicula ON Relacio.pelicula_id = Pelicula.id
                    LEFT JOIN ValoracioEstrelles ON Relacio.valoracio_id = ValoracioEstrelles.id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(
                    new Trio<>(
                        rs.getString("correuElectronic"),
                        rs.getString("titol"),
                        rs.getInt("puntuacio"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Trio<String, String, Integer> relacio) {
        // insert
        String sql = String.format("""
            INSERT INTO Pelicula_ValoracioEstrelles (client_id, pelicula_id, valoracio_id)
            VALUES (
               (SELECT id FROM Client WHERE correuElectronic = "%s"),
               (SELECT id FROM Pelicula WHERE titol = "%s"),
               (SELECT id FROM ValoracioEstrelles WHERE puntuacio = %d)
            );
        """, relacio.getElement1(), relacio.getElement2(), relacio.getElement3());
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
    public boolean delete(Trio<String, String, Integer> relacio){
        String sql = String.format("""
            DELETE FROM Pelicula_ValoracioEstrelles
            WHERE Pelicula_ValoracioEstrelles.ROWID IN (
                SELECT Pelicula_ValoracioEstrelles.ROWID
                FROM Pelicula_ValoracioEstrelles
                    LEFT JOIN Client ON Pelicula_ValoracioEstrelles.client_id = Client.id
                    LEFT JOIN Pelicula ON Pelicula_ValoracioEstrelles.pelicula_id = Pelicula.id
                    LEFT JOIN ValoracioEstrelles ON Pelicula_ValoracioEstrelles.valoracio_id = ValoracioEstrelles.id
                WHERE
                    Client.correuElectronic = "%s" AND
                    Pelicula.titol = "%s" AND
                    ValoracioEstrelles.puntuacio = %d
            );
        """, relacio.getElement1(), relacio.getElement2(), relacio.getElement3());
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
