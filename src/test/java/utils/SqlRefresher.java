package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlRefresher {

    public static void main(String[] args) {
        // Open the connection to the in-memory database
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            // Initialize the table structure
            stmt.execute("CREATE TABLE Employees (id INT, name VARCHAR(50), role VARCHAR(50))");
            System.out.println("Table 'Employees' created successfully.");

            // Seed the table with test data
            stmt.execute("INSERT INTO Employees VALUES (1, 'Naveen', 'Senior QA')");
            stmt.execute("INSERT INTO Employees VALUES (2, 'Rahul', 'Developer')");
            System.out.println("Data inserted successfully.\n");

            // Query for specific records
            String sqlQuery = "SELECT * FROM Employees WHERE role = 'Senior QA'";
            ResultSet rs = stmt.executeQuery(sqlQuery);

            System.out.println("--- SQL QUERY RESULTS ---");

            // Loop through the results and map the database columns to Java strings
            while (rs.next()) {
                String dbName = rs.getString("name");
                String dbRole = rs.getString("role");

                System.out.println("Found Record: Name = " + dbName + ", Role = " + dbRole);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}