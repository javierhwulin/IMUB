package ub.edu.resources.dao.DB.relationships;

import java.util.ArrayList;
import java.util.List;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioTematicaPelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAORelacioTematicaPeliculaDB extends DAORelacioDB<Parell<String, String>> implements DAORelacioTematicaPelicula{

    private final Connection connection;

    public DAORelacioTematicaPeliculaDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Parell<String, String>> getAll() {
        List<Parell<String, String>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Pelicula.titol,
                Tematica.nomTematica
            FROM
                Pelicula_Tematica
                    LEFT JOIN Pelicula ON Pelicula_Tematica.pelicula_id = Pelicula.id
                    LEFT JOIN Tematica ON Pelicula_Tematica.tematica_id = Tematica.id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(new Parell<>(rs.getString("nomTematica"), rs.getString("titol")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Parell<String, String> relacio) {
        int pelicula_id = 0;
        int tematica_id = 0;

        // query tematica_id
        String sql = String.format("""
            SELECT id FROM Tematica WHERE nomTematica='%s';
        """, relacio.getElement1());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                tematica_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // query pelicula_id
        sql = String.format("""
            SELECT id FROM Pelicula WHERE titol='%s';
        """, relacio.getElement2());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                pelicula_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // insert
        sql = String.format(
            "INSERT INTO Pelicula_Tematica ('pelicula_id', 'tematica_id') VALUES ('%s', '%s');",
            pelicula_id, tematica_id
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
    public boolean delete(Parell<String, String> relacio){
        String sql = String.format(
            """
            DELETE FROM Pelicula_Tematica WHERE Pelicula_Tematica.id IN (
                SELECT Pelicula_Tematica.id FROM Pelicula_Tematica
                    LEFT JOIN Pelicula ON Pelicula_Tematica.pelicula_id = Pelicula.id
                    LEFT JOIN Tematica ON Pelicula_Tematica.tematica_id = Tematica.id
                WHERE Pelicula.titol= '%s' AND Tematica.nomTematica = '%s'
            );
            """,
            relacio.getElement2(), relacio.getElement1()
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
