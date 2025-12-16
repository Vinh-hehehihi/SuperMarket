package fu.hsf302.supermaketmanagerproduct.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;

    private String employeeCode;
    private String fullName;
    private String phone;
    private String email;

    private String username;
    private String passwordHash;
    private String role;

    private Boolean isActive = true;

    public Employee() {
    }

    public Employee(String employeeCode, String fullName, String phone, String email, String username, String passwordHash, String role, Boolean isActive) {
        this.employeeCode = employeeCode;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}