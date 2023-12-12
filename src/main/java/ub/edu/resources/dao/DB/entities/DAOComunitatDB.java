package ub.edu.resources.dao.DB.entities;

import ub.edu.model.cataleg.Comunitat;
import ub.edu.resources.dao.entities.DAOComunitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOComunitatDB implements DAOComunitat {

    private final Connection connection;

    public DAOComunitatDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Comunitat> getAll() throws Exception {
        List<Comunitat> comunitats = new ArrayList<>();
        String sql = "SELECT * FROM Comunitat";
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                comunitats.add(new Comunitat(
                    rs.getString("nomComunitat"),
                    rs.getString("descripcio")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comunitats;
    }

    @Override
    public Optional<Comunitat> getById(String[] id) throws Exception {
        String nomComunitat = Objects.requireNonNull(id[0], "Comunitat name cannot be null");
        Comunitat comunitat = null;
        String sql = String.format(
            "SELECT * FROM Comunitat WHERE nomComunitat='%s';",
            nomComunitat
        );
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                comunitat = new Comunitat(
                    rs.getString("nomComunitat"),
                    rs.getString("descripcio")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(comunitat);
    }

    @Override
    public boolean add(Comunitat comunitat) throws Exception {
        String sql = String.format(
            """
            INSERT INTO Comunitat ('nomComunitat', 'descripcio')
            VALUES ('%s', '%s');
            """,
            comunitat.getNom(),
            comunitat.getDescripcio()
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
    public boolean update(Comunitat comunitat, String[] params) throws Exception {
        String sql = String.format(
            """
            UPDATE Comunitat
            SET nomComunitat='%s', descripcio='%s'
            WHERE nomComunitat='%s';
            """,
            params[0], params[1],
            comunitat.getNom()
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
    public boolean delete(Comunitat comunitat) throws Exception {
        String sql = String.format(
            "DELETE FROM Comunitat WHERE nomComunitat='%s';",
            comunitat.getNom()
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
