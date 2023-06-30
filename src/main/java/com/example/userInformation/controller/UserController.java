package com.example.userInformation.controller;
import com.example.userInformation.entity.User;
import com.example.userInformation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
//    private final UserService userService;

    @Autowired
    private UserService userService;


//    @GetMapping
//    public List<User> getUsersBySorting(
//            @RequestParam(value = "firstName", required = false) String firstName,
//            @RequestParam(value = "sort", defaultValue = "firstName") String sortField,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size
//    ) {
//        if (firstName != null) {
//            return userService.getUsersByFirstName(firstName);
//        } else {
//            return userService.getUsersSortedByField(sortField, page, size);
//        }
//    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortAttribute}")
    public ResponseEntity<Page<User>> displayAllDetails(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, @PathVariable("sortAttribute") String sortAttribute
    ) {
        Page<User> userPage = userService.getAllDetails(pageNumber, pageSize, sortAttribute);
        return ResponseEntity.ok(userPage);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        boolean isAcceptable = userService.checkUserAcceptance(user.getFirstName(), user.getLastName());

        if (isAcceptable) {
            userService.addUser(user);
            return ResponseEntity.ok("User details accepted.");
        } else {

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Firstname or lastname are already exists.");

        }

    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(value = "attribute") String attribute,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        List<User> users = userService.getUsers(attribute, page-1, size);
        return ResponseEntity.ok(users);
    }
}
