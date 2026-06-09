package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Employee;
import org.testng.Assert;
import utils.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseSteps {

    // Store the database row as an object to share state between steps
    private Employee actualEmployee;

    @Given("the backend database is initialized with employee data")
    public void the_backend_database_is_initialized_with_employee_data() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS Employees (id INT, name VARCHAR(50), role VARCHAR(50))");
            stmt.execute("DELETE FROM Employees");
            stmt.execute("INSERT INTO Employees VALUES (1, 'Naveen', 'Senior QA')");
            stmt.execute("INSERT INTO Employees VALUES (2, 'Rahul', 'Developer')");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Database Initialization Failed!");
        }
    }

    @When("I query the database for the employee named {string}")
    public void i_query_the_database_for_the_employee_named(String employeeName) {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            // Pull all columns so we can map the full employee profile
            String sqlQuery = "SELECT * FROM Employees WHERE name = '" + employeeName + "'";
            ResultSet rs = stmt.executeQuery(sqlQuery);

            if (rs.next()) {
                // Map the ResultSet row to our Employee model
                actualEmployee = new Employee();

                actualEmployee.setId(rs.getInt("id"));
                actualEmployee.setName(rs.getString("name"));
                actualEmployee.setRole(rs.getString("role"));

                // Print mapped object for debugging
                System.out.println("Mapped POJO: " + actualEmployee.toString());
            } else {
                Assert.fail("No record found in the database for: " + employeeName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("SQL Query Failed!");
        }
    }

    @Then("the database should return the role {string}")
    public void the_database_should_return_the_role(String expectedRole) {
        // Validate the database role against the expected feature file value
        Assert.assertEquals(actualEmployee.getRole(), expectedRole, "Database data integrity mismatch!");
    }
}