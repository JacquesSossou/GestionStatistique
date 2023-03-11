package com.rtr.repository;


import com.rtr.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

;

public interface UserRepository extends CrudRepository<User, Long> {

//  @Query("SELECT c FROM Customer c WHERE c.email = ?1")
//  public Customer findByEmail(String email);


  //@Query("SELECT c FROM User c WHERE c.email = ?1")
  @Query("SELECT c FROM User c WHERE c.email = ?1")
  Optional<User> findByEmail(String email);

  Boolean existsByEmail (String email);



   public Optional<User> findByResetPasswordToken(String resetPasswordToken);



}
