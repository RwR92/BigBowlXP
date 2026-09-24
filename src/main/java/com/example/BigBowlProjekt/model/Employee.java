package com.example.BigBowlProjekt.model;

public class Employee  {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String role;


    // Read
    public Employee(Long employeeId, String firstName, String lastName, String phoneNumber, String role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    } 

    // Create
    public Employee(String firstName, String lastName, String phoneNumber, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

        public String getLastName() {
        return lastName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

} //Employee
