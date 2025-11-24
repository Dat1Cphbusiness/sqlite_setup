package app.persistence;

import app.entities.Dog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogDAO {

    private DBConnector dbConnector;

    public DogDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS dogs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    age INTEGER NOT NULL,
                    breed TEXT,
                    nick_name TEXT,
                    weight REAL
                );
                """;

        try (
                Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public void insertDog(Dog dog) throws SQLException {
        String sql = "INSERT INTO dogs (name, age, breed, nick_name, weight) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = dbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, dog.getName());
            ps.setInt(2, dog.getAge());
            ps.setString(3, dog.getBreed());
            ps.setString(4, dog.getNickName());
            ps.setDouble(5, dog.getWeight());

            ps.executeUpdate();
        }
    }

    public List<Dog> findAll() throws SQLException {
        String sql = "SELECT id, name, age, breed, nick_name, weight FROM dogs";
        List<Dog> dogs = new ArrayList<>();

        try (
                Connection conn = dbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                dogs.add(new Dog(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("breed"), rs.getString("nick_name"), rs.getDouble("weight")));
            }
        }
        return dogs;
    }

    public Dog findById(int id) throws SQLException {
        String sql = "SELECT id, name, age, breed, nick_name, weight FROM dogs WHERE id = ?";

        try (
                Connection conn = dbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Dog(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("breed"), rs.getString("nick_name"), rs.getDouble("weight"));
            }
        }
        return null;
    }

    public void updateDog(int id, String name, int age, String breed, String nickName, double weight) throws SQLException {
        String sql = """
                    UPDATE dogs
                    SET name = ?, age = ?, breed = ?, nick_name = ?, weight = ?
                    WHERE id = ?
                """;

        try (
                Connection conn = dbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, breed);
            ps.setString(4, nickName);
            ps.setDouble(5, weight);
            ps.setInt(6, id);

            ps.executeUpdate();
        }
    }

    public void deleteDog(int id) throws SQLException {
        String sql = "DELETE FROM dogs WHERE id = ?";

        try (
                Connection conn = dbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

