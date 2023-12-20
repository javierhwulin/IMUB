package ub.edu.resources.dao.DB.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioTematicaComunitat;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DAORelacioTematicaComunitatDB extends DAORelacioDB<Parell<String, String>> implements DAORelacioTematicaComunitat {

    private final Connection connection;

    public DAORelacioTematicaComunitatDB(Connection c){
        this.connection = c;
    }

    @Override
    public List<Parell<String, String>> getAll() {
        List<Parell<String, String>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Comunitat.nomComunitat,
                Tematica.nomTematica
            FROM
                Comunitat_Tematica
                    LEFT JOIN Comunitat ON Comunitat_Tematica.comunitat_id = Comunitat.id
                    LEFT JOIN Tematica ON Comunitat_Tematica.tematica_id = Tematica.id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(new Parell<>(rs.getString("nomTematica"), rs.getString("nomComunitat")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Parell<String, String> relacio) {
        int comunitat_id = 0;
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

        // query comunitat_id
        sql = String.format("""
            SELECT id FROM Comunitat WHERE nomComunitat='%s';
        """, relacio.getElement2());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                comunitat_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // insert
        sql = String.format(
            "INSERT INTO Comunitat_Tematica ('comunitat_id', 'tematica_id') VALUES ('%s', '%s');",
            comunitat_id, tematica_id
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
            DELETE FROM Comunitat_Tematica WHERE Comunitat_Tematica.id IN (
                SELECT Comunitat_Tematica.id FROM Comunitat_Tematica
                    LEFT JOIN Pelicula ON Comunitat_Tematica.comunitat_id = Pelicula.id
                    LEFT JOIN Tematica ON Comunitat_Tematica.tematica_id = Tematica.id
                WHERE Comunitat.nomComunitat= '%s' AND Tematica.nomTematica = '%s'
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
