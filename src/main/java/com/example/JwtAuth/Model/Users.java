package com.example.JwtAuth.Model;


import jakarta.persistence.*;

@Entity
    @Table(name="JwtTable")
    public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public int id;
        public String name;
        public String email;
        public String password;
        public  String role;
        public String profileImg;

        public Users() {
            super();
        }

        public Users(int id, String name, String email, String password,String role,String profileImg) {
            super();
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.role=role;
            this.profileImg=profileImg;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPhoto(){return profileImg;}

        public void setPhoto(String profileImg){this.profileImg=profileImg;}
    }
