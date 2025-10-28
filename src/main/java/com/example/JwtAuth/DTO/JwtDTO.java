package com.example.JwtAuth.DTO;

public class JwtDTO {
    private String email;
    private String password;
    String token;

    public JwtDTO(String email, String password,String token) {
        this.email = email;
        this.password = password;
        this.token=token;
    }

    public JwtDTO() {
    }

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
