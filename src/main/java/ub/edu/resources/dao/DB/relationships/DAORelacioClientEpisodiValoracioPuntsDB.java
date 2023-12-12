package ub.edu.resources.dao.DB.relationships;


import ub.edu.model.ED.Quintet;
import ub.edu.resources.dao.relationships.DAORelacioClientEpisodiValoracioPunts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DAORelacioClientEpisodiValoracioPuntsDB extends DAORelacioDB<Quintet<String, String, Integer, Integer, Float>> implements DAORelacioClientEpisodiValoracioPunts {

    private final Connection connection;

    public DAORelacioClientEpisodiValoracioPuntsDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Quintet<String, String, Integer, Integer, Float>> getAll() {
        List<Quintet<String, String, Integer, Integer, Float>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Client.correuElectronic,
                Serie.titol,
                Temporada.numTemporada,
                Episodi.numEpisodi,
                ValoracioPunts.puntuacio
            FROM
                Episodi_ValoracioPunts AS Relacio
                    LEFT JOIN Client ON Relacio.client_id = Client.id
                    LEFT JOIN Episodi ON Relacio.episodi_id = Episodi.id
                    LEFT JOIN ValoracioPunts ON Relacio.valoracio_id = ValoracioPunts.id
                    LEFT JOIN Temporada ON Episodi.temporada_id = Temporada.id
                    LEFT JOIN Serie ON Temporada.serie_id = Serie.id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(
                    new Quintet<>(
                        rs.getString("correuElectronic"),
                        rs.getString("titol"),
                        rs.getInt("numTemporada"),
                        rs.getInt("numEpisodi"),
                        rs.getFloat("puntuacio")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Quintet<String, String, Integer, Integer, Float> relacio) {
        // insert
        String sql = String.format("""
            INSERT INTO Episodi_ValoracioPunts (client_id, episodi_id, valoracio_id)
            VALUES (
               (SELECT id FROM Client WHERE correuElectronic = "%s"),
               (
                    SELECT Episodi.id
                    FROM Episodi
                        LEFT JOIN Temporada ON Episodi.temporada_id = Temporada.id
                        LEFT JOIN Serie ON Temporada.serie_id = Serie.id
                    WHERE
                        Serie.titol = "%s" AND
                        Temporada.numTemporada = %d AND
                        Episodi.numEpisodi = %d
               ),
                (SELECT id FROM ValoracioPunts WHERE puntuacio = %.2f)
            );
        """,
            relacio.getElement1(),  // client.correu
            relacio.getElement2(),  // serie.titol
            relacio.getElement3(),  // temporada.num
            relacio.getElement4(),  // episodi.num
            relacio.getElement5()  // valoracio.puntuacio
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
    public boolean delete(Quintet<String, String, Integer, Integer, Float> relacio){
        String sql = String.format("""
            DELETE FROM Episodi_ValoracioPunts
            WHERE Episodi_ValoracioPunts.ROWID IN (
                SELECT Episodi_ValoracioPunts.ROWID
                FROM Episodi_ValoracioPunts
                    LEFT JOIN Client ON Episodi_ValoracioPunts.client_id = Client.id
                    LEFT JOIN Episodi ON Episodi_ValoracioPunts.episodi_id = Episodi.id
                    LEFT JOIN ValoracioPunts ON Episodi_ValoracioPunts.valoracio_id = ValoracioPunts.id
                    LEFT JOIN Temporada ON Episodi.temporada_id = Temporada.id
                    LEFT JOIN Serie ON Temporada.serie_id = Serie.id
                WHERE
                    Client.correuElectronic = "%s" AND
                    Serie.titol = "%s" AND
                    Temporada.numTemporada = %d AND
                    Episodi.numEpisodi = %d AND
                    ValoracioPunts.puntuacio = %.2f
            );
        """,
            relacio.getElement1(),  // client.correu
            relacio.getElement2(),  // serie.titol
            relacio.getElement3(),  // temporada.num
            relacio.getElement4(),  // episodi.num
            relacio.getElement5()  // valoracio.puntuacio
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
