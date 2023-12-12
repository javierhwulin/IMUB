package ub.edu.resources.dao.DB.entities;

import ub.edu.model.cataleg.Pelicula;
import ub.edu.resources.dao.entities.DAOPelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOPeliculaDB implements DAOPelicula {

    private final Connection connection;

    public DAOPeliculaDB(Connection c){
        this.connection = c;
    }

    @Override
    public List<Pelicula> getAll() throws Exception {
        List<Pelicula> pelis = new ArrayList<>();
        String sql = "SELECT * FROM Pelicula";
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                pelis.add(new Pelicula(
                    rs.getString("titol"),
                    rs.getString("descripcio"),
                    rs.getString("imatge"),
                    rs.getInt("anyPrimeraEmissio"),
                    rs.getString("idioma"),
                    rs.getFloat("valoracio")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pelis;
    }

    @Override
    public Optional<Pelicula> getById(String[] id) throws Exception {
        Pelicula peli = null;
        String sql = String.format(
            "SELECT * FROM Pelicula WHERE titol='%s';",
            id[0]
        );
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                peli = new Pelicula(
                        rs.getString("titol"),
                        rs.getString("descripcio"),
                        rs.getString("imatge"),
                        rs.getInt("anyPrimeraEmissio"),
                        rs.getString("idioma"),
                        rs.getFloat("valoracio")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(peli);
    }

    @Override
    public boolean add(Pelicula pelicula) throws Exception {
        String sql = String.format(
            """
            INSERT INTO Pelicula ('titol', 'descripcio', 'imatge', 'anyPrimeraEmissio', 'idioma', 'valoracio')
            VALUES ('%s', '%s', '%s', '%d', '%s', '%f');
            """,
            pelicula.getNom(),
            pelicula.getDescripcio(),
            pelicula.getURL(),
            pelicula.getAnyEstrena(),
            pelicula.getIdioma(),
            pelicula.getValoracioInicial()
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
    public boolean update(Pelicula pelicula, String[] params) throws Exception {
        String sql = String.format(
            """
            UPDATE Pelicula
            SET titol='%s', descripcio='%s', imatge='%s', anyPrimeraEmissio='%d', idioma='%s', valoracio='%f'
            WHERE titol='%s';
            """,
            params[0], params[1], params[2], params[3], params[4], params[5],
            pelicula.getNom()
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
    public boolean delete(Pelicula pelicula) throws Exception {
        String sql = String.format(
            "DELETE FROM Pelicula WHERE titol='%s';",
            pelicula.getNom()
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
