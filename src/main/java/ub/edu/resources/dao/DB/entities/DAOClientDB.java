package ub.edu.resources.dao.DB.entities;

import ub.edu.model.Client;
import ub.edu.resources.dao.entities.DAOClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOClientDB implements DAOClient {

    private final Connection connection;

    public DAOClientDB(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT correuElectronic, contrasenya FROM Client";
        try (
            ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                clients.add(new Client(rs.getString("correuElectronic"), rs.getString("contrasenya")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> getById(String[] id) {
        Client client = null;
        String sql = String.format(
            "SELECT correuElectronic, contrasenya FROM Client WHERE correuElectronic='%s';",
            id[0]
        );
        try (
                ResultSet rs  = this.connection.createStatement().executeQuery(sql);
        ){
            while (rs.next()) {
                client = new Client(rs.getString("correuElectronic"), rs.getString("contrasenya"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(client);
    }

    @Override
    public boolean add(final Client client) {
        String sql = String.format(
            "INSERT INTO Client ('correuElectronic', 'contrasenya') VALUES ('%s', '%s');",
            client.getNom(), client.getPwd()
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
    public boolean update(final Client client, String[] params) {
        String name = Objects.requireNonNull(params[0], "Name cannot be null");
        String pwd = Objects.requireNonNull(params[1], "Password cannot be null");

        String sql = String.format(
            "UPDATE Client SET correuElectronic='%s', contrasenya='%s' WHERE correuElectronic='%s';",
            name, pwd, client.getNom()
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
    public boolean delete(final Client client) {
        String sql = String.format(
            "DELETE FROM Client WHERE correuElectronic='%s';",
            client.getNom()
        );
        try (
                PreparedStatement statement = this.connection.prepareStatement(sql);
        ){
            System.out.println(statement.executeUpdate());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
