package ub.edu.resources.dao.DB.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.model.ED.Quintet;
import ub.edu.resources.dao.relationships.DAORelacioClientEpisodiValoracioLikes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DAORelacioClientEpisodiValoracioLikesDB extends DAORelacioDB<Quintet<String, String, Integer, Integer, Boolean>> implements DAORelacioClientEpisodiValoracioLikes {

    private final Connection connection;

    public DAORelacioClientEpisodiValoracioLikesDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Quintet<String, String, Integer, Integer, Boolean>> getAll() {
        List<Quintet<String, String, Integer, Integer, Boolean>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Client.correuElectronic,
                Serie.titol,
                Temporada.numTemporada,
                Episodi.numEpisodi,
                ValoracioLikes.puntuacio
            FROM
                Episodi_ValoracioLikes AS Relacio
                    LEFT JOIN Client ON Relacio.client_id = Client.id
                    LEFT JOIN Episodi ON Relacio.episodi_id = Episodi.id
                    LEFT JOIN ValoracioLikes ON Relacio.valoracio_id = ValoracioLikes.id
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
                        rs.getBoolean("puntuacio")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Quintet<String, String, Integer, Integer, Boolean> relacio) {
        // insert
        String sql = String.format("""
            INSERT INTO Episodi_ValoracioLikes (client_id, episodi_id, valoracio_id)
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
                (SELECT id FROM ValoracioLikes WHERE puntuacio = %b)
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
    public boolean delete(Quintet<String, String, Integer, Integer, Boolean> relacio){
        String sql = String.format("""
            DELETE FROM Episodi_ValoracioLikes
            WHERE Episodi_ValoracioLikes.ROWID IN (
                SELECT Episodi_ValoracioLikes.ROWID
                FROM Episodi_ValoracioLikes
                    LEFT JOIN Client ON Episodi_ValoracioLikes.client_id = Client.id
                    LEFT JOIN Episodi ON Episodi_ValoracioLikes.episodi_id = Episodi.id
                    LEFT JOIN ValoracioLikes ON Episodi_ValoracioLikes.valoracio_id = ValoracioLikes.id
                    LEFT JOIN Temporada ON Episodi.temporada_id = Temporada.id
                    LEFT JOIN Serie ON Temporada.serie_id = Serie.id
                WHERE
                    Client.correuElectronic = "%s" AND
                    Serie.titol = "%s" AND
                    Temporada.numTemporada = %d AND
                    Episodi.numEpisodi = %d AND
                    ValoracioLikes.puntuacio = %b
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
