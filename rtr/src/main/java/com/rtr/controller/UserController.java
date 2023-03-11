package com.rtr.controller;


import com.rtr.model.Role;
import com.rtr.model.User;
import com.rtr.service.EtatService;
import com.rtr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    PasswordEncoder passwordEncoder;
    @PostMapping("/user")
    public User createUser(@RequestBody User User) {
        return userService.saveUser(User);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> User = userService.getUser(id);
        if(User.isPresent()) {
            return User.get();
        } else {
            return null;
        }
    }




    @GetMapping("/users")
    public Iterable<User> getUsers() {

        return userService.getUsers();
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User User) {
        Optional<User> p = userService.getUser(id);
        if(p.isPresent()) {
            User currentUser = p.get();

            String nom = User.getFirstname();
            if(nom != null) {
                currentUser.setFirstname(nom);
            }

            String prenoms = User.getLastname();
            if(nom != null) {
                currentUser.setLastname(prenoms);
            }

            String email = User.getEmail();
            if(email != null) {
                currentUser.setEmail(email);
            }

            int activated = User.getActivated();
            if(activated != 0 || activated != 1) {
                currentUser.setEmail(email);
            }

            String role = String.valueOf(User.getRole());
            if(role != null) {
                currentUser.setRole(Role.valueOf(role));
            }


            userService.saveUser(currentUser);
            return currentUser;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/user/{id}")
    public void deleteUser(@PathVariable ("id") final Long id) {
        userService.deleteUser(id);
    }

}
