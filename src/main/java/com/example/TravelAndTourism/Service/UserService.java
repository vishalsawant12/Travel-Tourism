package com.example.TravelAndTourism.Service;

import com.example.TravelAndTourism.models.User;
import com.example.TravelAndTourism.repository.UserRepository;
import com.example.TravelAndTourism.responsewrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepository;

    @Autowired
    private ResponseWrapper responseWrapper;

//    create user

    public ResponseEntity<?> createUser(User user) {
        Optional<User> existingUserByName=userRepository.findByUsername(user.getUsername());
        if (existingUserByName.isPresent()){
            responseWrapper.setMessage("user already exist");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);

        }
        User saveUser = userRepository.save(user);
        responseWrapper.setMessage("user created sucessfully");
        responseWrapper.setData(saveUser);
        return new ResponseEntity<>(responseWrapper,HttpStatus.CREATED);
    }
//    update user

    public ResponseEntity<?> updateUser(Long id, User userDetails){
        Optional<User> existingUserOpt= userRepository.findById(id);

        if (!existingUserOpt.isPresent()){
            responseWrapper.setMessage("user not found");
            responseWrapper.setData(null);
            return new ResponseEntity(responseWrapper, HttpStatus.NOT_FOUND);
        }
        User existingUser = existingUserOpt.get();

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setRoles(userDetails.getRoles());
        existingUser.setActive(userDetails.isActive());

        User updateUser = userRepository.save(existingUser);
        responseWrapper.setMessage("user update sucessfully");
        responseWrapper.setData(updateUser);
        return  new ResponseEntity(responseWrapper,HttpStatus.OK);

    }

// Get all users
public ResponseEntity<?> getAllUsers() {
    List<User> users = userRepository.findAll();

    if (users.isEmpty()) {
        responseWrapper.setMessage("No users found");
        responseWrapper.setData(null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

    responseWrapper.setMessage("Users retrieved successfully");
    responseWrapper.setData(users);
    return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
}

//     Get user by ID
    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (!existingUserOpt.isPresent()) {
            responseWrapper.setMessage("User not found");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        }

        responseWrapper.setMessage("User retrieved successfully");
        responseWrapper.setData(existingUserOpt.get());
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }




    // delete user by id
    public  ResponseEntity<?> deleteUser(Long id){
        Optional<User> exisitingUserOpt = userRepository.findById(id);

        if (!exisitingUserOpt.isPresent()) {
            responseWrapper.setMessage("User not found");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        responseWrapper.setMessage("user deleted sucessfully");
        responseWrapper.setData(null);
        return  new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }


}
