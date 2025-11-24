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

        System.out.println("\nDog with id 2:");
        System.out.println(dogDAO.findById(2));

        // Update
        System.out.println("\nUpdating dog with id 2...");
        dogDAO.updateDog(2, "Maximus", 6, "German Shepherd", "Max", 35.0);
        System.out.println(dogDAO.findById(2));

        // Delete
        System.out.println("\nDeleting dog with id 1...");
        dogDAO.deleteDog(1);

        System.out.println("\nSå udskriver alle igen");

        for (Dog dog : dogs) {
            System.out.println(dog);
        }

    }
    }