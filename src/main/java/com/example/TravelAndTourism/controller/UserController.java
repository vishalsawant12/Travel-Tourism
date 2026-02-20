package com.example.TravelAndTourism.controller;

import com.example.TravelAndTourism.Service.UserService;
import com.example.TravelAndTourism.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

//create
@PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }
//Get all user
@GetMapping("/users")
public ResponseEntity<?> getAllUsers() {
    return userService.getAllUsers();
}

//  update

    @PutMapping("users/{id}")
public ResponseEntity<?> updateUser(@PathVariable Long id ,@RequestBody User userDetails){
    return  userService.updateUser(id,userDetails);
   }

//     Get user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

// deleteById

    @DeleteMapping("/users/{id}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
