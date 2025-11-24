package app.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private final String URL;

    public DBConnector(String URL) {
        this.URL = URL;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

