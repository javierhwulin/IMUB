package ub.edu.resources.dao.DB.entities;

import ub.edu.model.cataleg.Serie;
import ub.edu.resources.dao.entities.DAOSerie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOSerieDB implements DAOSerie {

    private final Connection connection;

    public DAOSerieDB(Connection c){
        this.connection = c;
    }

    @Override
    public List<Serie> getAll() throws Exception {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM Serie";
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                series.add(new Serie(
                    rs.getString("titol"),
                    rs.getString("descripcio"),
                    rs.getString("imatge"),
                    rs.getInt("anyPrimeraEmissio"),
                    rs.getString("idioma"),
                    rs.getInt("durada")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return series;
    }

    @Override
    public Optional<Serie> getById(String[] id) throws Exception {
        Serie serie = null;
        String sql = String.format(
            "SELECT * FROM Serie WHERE titol='%s';",
            id[0]
        );
        try (
                ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                serie = new Serie(
                    rs.getString("titol"),
                    rs.getString("descripcio"),
                    rs.getString("imatge"),
                    rs.getInt("anyPrimeraEmissio"),
                    rs.getString("idioma"),
                    rs.getInt("durada")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(serie);
    }

    @Override
    public boolean add(Serie serie) throws Exception {
        String sql = String.format(
            """
            INSERT INTO Serie ('titol', 'descripcio', 'imatge', 'anyPrimeraEmissio', 'idioma', 'durada')
            VALUES ('%s', '%s', '%s', '%d', '%s', '%d');
            """,
            serie.getNom(),
            serie.getDescripcio(),
            serie.getImatgeUrl(),
            serie.getAnyEstrena(),
            serie.getIdioma(),
            serie.getDurada()
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
    public boolean update(Serie serie, String[] params) throws Exception {
        String sql = String.format(
            """
            UPDATE Serie
            SET titol='%s', descripcio='%s', imatge='%s', anyPrimeraEmissio='%d', idioma='%s', durada='%f'
            WHERE titol='%s';
            """,
            params[0], params[1], params[2], params[3], params[4], params[5],
            serie.getNom()
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
    public boolean delete(Serie serie) throws Exception {
        String sql = String.format(
            "DELETE FROM Serie WHERE titol='%s';",
            serie.getNom()
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
