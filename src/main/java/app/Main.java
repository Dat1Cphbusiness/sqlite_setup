package app;

import app.entities.Dog;
import app.persistence.DBConnector;
import app.persistence.DogDAO;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:sqlite:data/dogs.db";
        DBConnector dbConnector = new DBConnector(URL);
        DogDAO dogDAO = new DogDAO(dbConnector);

        System.out.println("\nAll dogs:");

        List<Dog> dogs = dogDAO.findAll();

        for (Dog dog : dogs) {
            System.out.println(dog);
        }

        }
    }