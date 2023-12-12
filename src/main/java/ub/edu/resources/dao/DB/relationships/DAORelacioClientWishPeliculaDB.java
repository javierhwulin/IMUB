package ub.edu.resources.dao.DB.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioClientWishPelicula;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DAORelacioClientWishPeliculaDB extends DAORelacioDB<Parell<String, String>> implements DAORelacioClientWishPelicula {


    private final Connection connection;

    SimpleDateFormat formatter;

    public DAORelacioClientWishPeliculaDB(Connection c) {
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.connection = c;
    }

    private String formatDate(Date date) {
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        String data = null;
        return formatter.format(date);
    }

    @Override
    public List<Parell<String, String>> getAll() throws Exception {
        List<Parell<String, String>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Client.correuElectronic,
                Pelicula.titol
            FROM
                DesigPelicula
                    LEFT JOIN Client ON Client.id = DesigPelicula.client_id
                    LEFT JOIN Pelicula ON Pelicula.id = DesigPelicula.pelicula_id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(
                    new Parell(
                        rs.getString("correuElectronic"),
                        rs.getString("titol")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Parell<String, String> relacio) throws Exception {
        // insert
        String sql = String.format("""
            INSERT INTO DesigPelicula (client_id, pelicula_id, valoracio_id)
            VALUES (
               (SELECT id FROM Client WHERE correuElectronic = "%s"),
               (SELECT id FROM Pelicula WHERE titol = "%s"),
               "%s"
            );
        """, relacio.getElement1(), relacio.getElement2(), this.formatDate(new Date()));
        System.out.println(sql);
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
    public boolean delete(Parell<String, String> relacio) throws Exception {
        String sql = String.format("""
            DELETE FROM DesigPelicula
            WHERE DesigPelicula.ROWID IN (
                SELECT DesigPelicula.ROWID
                FROM DesigPelicula
                    LEFT JOIN Client ON DesigPelicula.client_id = Client.id
                    LEFT JOIN Pelicula ON DesigPelicula.pelicula_id = Pelicula.id
                WHERE
                    Client.correuElectronic = "%s" AND
                    Pelicula.titol = "%s" AND
            );
        """, relacio.getElement1(), relacio.getElement2());
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
