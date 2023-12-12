package ub.edu.resources.dao.DB.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioClientComunitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DAORelacioClientComunitatDB extends DAORelacioDB<Parell<String, String>> implements DAORelacioClientComunitat {

    private final Connection connection;

    public DAORelacioClientComunitatDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Parell<String, String>> getAll() {
        List<Parell<String, String>> relacions = new ArrayList<>();
        String sql = """
            SELECT
                Client.correuElectronic,
                Comunitat.nomComunitat
            FROM
                Client_Comunitat
                    LEFT JOIN Client ON Client_Comunitat.client_id = Client.id
                    LEFT JOIN Comunitat ON Client_Comunitat.comunitat_id = Comunitat.id
        """;
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                relacions.add(new Parell<>(rs.getString("correuElectronic"), rs.getString("nomComunitat")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relacions;
    }

    @Override
    public boolean add(Parell<String, String> relacio) {
        int client_id = 0;
        int comunitat_id = 0;

        // query comunitat_id
        String sql = String.format("""
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

        // query client_id
        sql = String.format("""
            SELECT id FROM Client WHERE correuElectronic='%s';
        """, relacio.getElement1());
        try (
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
        ){
            if (rs.next()) {
                client_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // insert
        sql = String.format(
            "INSERT INTO Client_Comunitat ('client_id', 'comunitat_id') VALUES ('%s', '%s');",
            client_id, comunitat_id
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
            DELETE FROM Client_Comunitat WHERE Client_Comunitat.id IN (
                SELECT Client_Comunitat.id FROM Client_Comunitat
                    LEFT JOIN Client ON Client_Comunitat.client_id = Client.id
                    LEFT JOIN Comunitat ON Client_Comunitat.comunitat_id = Comunitat.id
                WHERE Client.correuElectronic = '%s' AND Comunitat.nomComunitat = '%s'
            );
            """,
            relacio.getElement1(), relacio.getElement2()
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
