package net.fadi.jpa.controller;

import net.fadi.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.fadi.jpa.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    // add user by id
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @PutMapping("/addRoleToAllUsers/{roleName}")
    public ResponseEntity<String> addRoleToAllUsers(@PathVariable String roleName){
        userService.addRoleToAllUsers(roleName);
        return ResponseEntity.ok("add role to all users Successfully!");
    }
}
