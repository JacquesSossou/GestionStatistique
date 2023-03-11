package com.rtr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur")
public class User implements UserDetails {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  @CreationTimestamp
  private Date creationDate;

  @UpdateTimestamp
  private Date updatedDate;

  private int activated;

  @OneToMany(mappedBy = "user")
  private Set<Reclamation> reclamations;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String resetPasswordToken;



  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  public User(String firstname, String lastname, String email, String password, int activated, Role role) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.activated = activated;
    this.role = role;
  }

  public User(String email, String password, int activated, Role role) {
    this.email = email;
    this.password = password;
    this.activated = activated;
    this.role = role;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
