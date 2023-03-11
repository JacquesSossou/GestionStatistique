package com.rtr.service;

import com.rtr.model.Etat;
import com.rtr.model.User;
import com.rtr.repository.EtatRepository;
import com.rtr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Optional<User> getUser(final Long id){
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }

    public User saveUser(User etat){
    String pw = etat.getPassword();
    etat.setPassword(passwordEncoder.encode(pw));
        return userRepository.save(etat);
    }
}
