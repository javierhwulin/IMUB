package ub.edu.resources.dao.DB.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.model.ED.Trio;
import ub.edu.resources.dao.relationships.DAORelacioClientPeliculaValoracioLikes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DAORelacioClientPeliculaValoracioLikesDB extends DAORelacioDB<Trio<String, String, Boolean>> implements DAORelacioClientPeliculaValoracioLikes {

    private final Connection connection;

    public DAORelacioClientPeliculaValoracioLikesDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Trio<String, String, Boolean>> getAll() {
        List<Trio<String, String, Boolean>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Client.correuElectronic,
                Pelicula.titol,
                ValoracioLikes.puntuacio
            FROM
                Pelicula_ValoracioLikes AS Relacio
                    LEFT JOIN Client ON Relacio.client_id = Client.id
                    LEFT JOIN Pelicula ON Relacio.pelicula_id = Pelicula.id
                    LEFT JOIN ValoracioLikes ON Relacio.valoracio_id = ValoracioLikes.id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(
                    new Trio<>(
                        rs.getString("correuElectronic"),
                        rs.getString("titol"),
                        rs.getBoolean("puntuacio"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Trio<String, String, Boolean> relacio) {
        // insert
        String sql = String.format("""
            INSERT INTO Pelicula_ValoracioLikes (client_id, pelicula_id, valoracio_id)
            VALUES (
               (SELECT id FROM Client WHERE correuElectronic = "%s"),
               (SELECT id FROM Pelicula WHERE titol = "%s"),
               (SELECT id FROM ValoracioLikes WHERE puntuacio = %b)
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
    public boolean delete(Trio<String, String, Boolean> relacio){
        String sql = String.format("""
            DELETE FROM Pelicula_ValoracioLikes
            WHERE Pelicula_ValoracioLikes.ROWID IN (
                SELECT Pelicula_ValoracioLikes.ROWID
                FROM Pelicula_ValoracioLikes
                    LEFT JOIN Client ON Pelicula_ValoracioLikes.client_id = Client.id
                    LEFT JOIN Pelicula ON Pelicula_ValoracioLikes.pelicula_id = Pelicula.id
                    LEFT JOIN ValoracioLikes ON Pelicula_ValoracioLikes.valoracio_id = ValoracioLikes.id
                WHERE
                    Client.correuElectronic = "%s" AND
                    Pelicula.titol = "%s" AND
                    ValoracioLikes.puntuacio = %b
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
