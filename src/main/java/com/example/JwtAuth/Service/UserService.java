package com.example.JwtAuth.Service;

import com.example.JwtAuth.DTO.JwtDTO;
import com.example.JwtAuth.DTO.LoginDTO;
import com.example.JwtAuth.Model.Users;

import java.util.List;

public interface UserService {

    public String createUser(Users newUser);
    public JwtDTO   signinUser(String email,String password);
    public JwtDTO signinWithHashing(String email, String password);
    public List<Users> getAllUser();
    public Users getUser(int id);
    public String updateUser(int id,Users editUser);
    public String deleteUser(int id);
//    public UserDetails findUser(String email);
//    public Products uploadProductImage(Products product, MultipartFile imageFile) throws IOException;
    public String saveUserRecord(Users userObj);
    public Users getUserById(int id);
}

