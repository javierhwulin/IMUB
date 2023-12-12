package ub.edu.resources.dao.DB.entities;

import ub.edu.model.cataleg.Tematica;
import ub.edu.resources.dao.entities.DAOTematica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOTematicaDB implements DAOTematica {

    private final Connection connection;

    public DAOTematicaDB(Connection c){
        this.connection = c;
    }

    @Override
    public List<Tematica> getAll() throws Exception {
        List<Tematica> tematiques = new ArrayList<>();
        String sql = "SELECT * FROM Tematica";
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                tematiques.add(new Tematica(rs.getString("nomTematica")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tematiques;
    }

    @Override
    public Optional<Tematica> getById(String[] id) throws Exception {
        Objects.requireNonNull(id[0], "Tematica name cannot be null");
        Tematica tematica = null;
        String sql = String.format(
            "SELECT * FROM Tematica WHERE nomTematica='%s';",
            id[0]
        );
        try (
                ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                tematica = new Tematica(rs.getString("nomTematica"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(tematica);
    }

    @Override
    public boolean add(Tematica tematica) throws Exception {
        String sql = String.format(
            """
            INSERT INTO Tematica ('nomTematica')
            VALUES ('%s');
            """,
            tematica.getNomTematica()
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
    public boolean update(Tematica tematica, String[] params) throws Exception {
        String name = Objects.requireNonNull(params[0], "Name cannot be null");
        String sql = String.format(
            """
            UPDATE Tematica
            SET nomTematica='%s',
            WHERE nomTematica='%s';
            """,
            name,
            tematica.getNomTematica()
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
    public boolean delete(Tematica tematica) throws Exception {
        String sql = String.format(
            "DELETE FROM Tematica WHERE nomTematica='%s';",
            tematica.getNomTematica()
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
