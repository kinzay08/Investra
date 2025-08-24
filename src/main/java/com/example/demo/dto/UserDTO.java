package com.example.demo.dto;

// Interface to simulate a tagging/marker interface
interface Validatable {}

// Base class to show inheritance
class BaseDTO {
    public void printBasicInfo() {
        System.out.println("This is a Data Transfer Object (DTO).");
    }
}

public class UserDTO extends BaseDTO implements Validatable {
    private String username;
    private String email;
    private String password;

    // Manually defined getter methods
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Optional: Constructor if needed
    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public void displayRoleType() {
        System.out.println("UserDTO: This is a standard user.");
    }

    // DUMMY INNER CLASS for polymorphism example
    public static class AdminDTO extends UserDTO {
        public AdminDTO(String username, String email, String password) {
            super(username, email, password);
        }

        @Override
        public void displayRoleType() {
            System.out.println("AdminDTO: This is an admin user.");
        }
    }


    public static void demoPolymorphism() {
        UserDTO user = new UserDTO("john", "john@example.com", "1234");
        UserDTO admin = new AdminDTO("admin", "admin@example.com", "admin123");

        user.displayRoleType();
        admin.displayRoleType();
    }
}
