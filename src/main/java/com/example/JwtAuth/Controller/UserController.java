package com.example.JwtAuth.Controller;


import com.example.JwtAuth.DTO.JwtDTO;
import com.example.JwtAuth.DTO.LoginDTO;
import com.example.JwtAuth.Model.Users;
import com.example.JwtAuth.Security.JwtUtil;

import com.example.JwtAuth.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

    @RestController
    @RequestMapping("/User")
    public class UserController {
        public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/ImageDir/Images";
        @Autowired
        UserServiceImpl service;
        @Autowired
        JwtUtil jwtutilObj;

        @PostMapping("/addUser")
        public String registerUser(@RequestBody Users newuser) {

            return service.createUser(newuser);
        }

        @PostMapping("/loginWithToken")
        public JwtDTO tokenLoginMethod(@RequestBody LoginDTO logindtoObj) {
            return service.signinUser(logindtoObj.getEmail(), logindtoObj.getPassword());

        }

        @PostMapping("/loginHashing")
        public JwtDTO loginWithHashing(@RequestBody JwtDTO userObj) {
            return service.signinWithHashing(userObj.getEmail(), userObj.getPassword());
        }

        @PutMapping("/updateUser/{id}")
        public String updateUser(@RequestHeader("Authorization") String token,
                                 @PathVariable int id,
                                 @RequestBody Users updatedUser) {

            token = token.replace("Bearer ", "");


            int tokenId = jwtutilObj.extractUserId(token);

            if (tokenId != id) {
                return "Unauthorized to update this user";
            }

            return service.updateUser(id, updatedUser);
        }

        @GetMapping("/getall")

        public List<Users> gettAllUsers() {
            return service.getAllUser();
        }

        @GetMapping("/getOne/{id}")
        public Users getOneUser(@PathVariable int id) {
            return service.getUser(id);
        }

        @GetMapping("/admin/dashboard")
        @PreAuthorize("hasRole('ADMIN')")
        public String adminPage() {
            return "Welcome Admin!";
        }

        @GetMapping("/user/profile")
        @PreAuthorize("hasRole('USER')")
        public String userPage() {
            return "Welcome User!";
        }

//        @PostMapping("/admin/addproductimage")
//        public ResponseEntity<?> uploadProductImage(@RequestPart Products product,
//                                                    @RequestPart MultipartFile imageFile) {
//            try {
//                Products product1 = service.uploadProductImage(product, imageFile);
//                return new ResponseEntity<>(product1, HttpStatus.CREATED);
//            } catch (Exception e) {
//                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }


        @PostMapping("/saveUserData")
        public String saveUserinDB(@ModelAttribute Users user, @RequestParam("profileImg") MultipartFile file) throws IOException {
            String originalFileNmae = file.getOriginalFilename();
            Path filenameAndPath = Paths.get(uploadDirectory, originalFileNmae);
            Files.write(filenameAndPath, file.getBytes());
            user.setPhoto(filenameAndPath.toString());

            return service.saveUserRecord(user);

        }

        @PostMapping("/getuserbyid/{id}")
        public ResponseEntity<Users> getById(@PathVariable int id) {
            Users user = service.getUserById(id);
            return ResponseEntity.ok().body(user);
        }

    }

