package models;

public class Employee {

    // Matches the columns in the Employees SQL table
    private int id;
    private String name;
    private String role;

    // Empty constructor needed for database mapping
    public Employee() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Formats the object for readable console logs
    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", Role=" + role + "]";
    }
}