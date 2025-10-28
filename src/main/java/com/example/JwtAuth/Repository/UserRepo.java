package com.example.JwtAuth.Repository;

import com.example.JwtAuth.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {
        Users findByEmailAndPassword(String email, String password);
        Users findByEmail(String email);

    }
