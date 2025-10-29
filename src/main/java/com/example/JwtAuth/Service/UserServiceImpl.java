package com.example.JwtAuth.Service;

import com.example.JwtAuth.DTO.JwtDTO;
import com.example.JwtAuth.DTO.LoginDTO;
import com.example.JwtAuth.Model.Users;
import com.example.JwtAuth.Repository.UserRepo;
import com.example.JwtAuth.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userrepo;
    @Autowired
    JwtUtil jwtutilObj;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
     BCryptPasswordEncoder passwordEncoder;

    @Override
    public String createUser(Users newUser) {
        String encodedPassword=encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        userrepo.save(newUser);
        return "User registered successfully";
    }

    @Override
    public JwtDTO signinUser(String email,String password) {
        Users user=userrepo.findByEmail(email);

        if(user==null && !passwordEncoder.matches(password,user.getPassword()) ){
            return new JwtDTO(email,"","Login failed");
        }
        String token=jwtutilObj.generateToken(user);
        return new JwtDTO(email,"",token);
    }

    @Override
    public JwtDTO signinWithHashing(String email,String password){
        Users user=userrepo.findByEmail(email);

        if(user==null && !passwordEncoder.matches(password,user.getPassword()) ){
            return new JwtDTO(email,"","Login failed");
        }
        String token=jwtutilObj.generateToken(user);
        return new JwtDTO(email,"",token);

    }

    @Override
    public List<Users> getAllUser() {
        return userrepo.findAll();
    }

    @Override
    public Users getUser(int id) {
        return userrepo.findById(id).orElseThrow(()->new RuntimeException("No user found"));
    }

    @Override
    public String updateUser(int id, Users editUser) {
        Optional<Users> existingUser=userrepo.findById(id);
        if(existingUser.isPresent()){
            Users user=existingUser.get();
            user.setName(editUser.getName());
            user.setEmail(editUser.getEmail());
            user.setPassword(editUser.getPassword());
            userrepo.save(user);
            return "User Updated successfully";
        }
        return  "User not found";
    }

    @Override
    public String deleteUser(int id) {
        Optional<Users> existingUser=userrepo.findById(id);
        if(existingUser.isPresent()){

            userrepo.deleteById(id);
            return "User deleted successfully";
        }
        return "No user found";

    }

    @Override
    public String saveUserRecord(Users userObj) {
        userrepo.save(userObj);
        return "User registered successfully";
    }

//    @Override
//    public UserDetails findUser(String email) throws UsernameNotFoundException {
//        Users user = userrepo.findByEmail(email);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User Not found");
//        }
//
//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//    @Override
//    public Products uploadProductImage(Products product, MultipartFile imageFile) throws IOException {
//        return null;
//    }
//
//    @Override
//    public String saveUserRecord(Users userObj) {
//        userrepo.save(userObj);
//        return "User saved Successfully" ;
//    }

    @Override
    public  Users getUserById(int id) {
        return userrepo.findById(id).orElseThrow(()->new RuntimeException("No user found"));
    }


}
