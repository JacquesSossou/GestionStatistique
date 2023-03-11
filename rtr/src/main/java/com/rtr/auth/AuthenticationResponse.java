package com.rtr.auth;

import com.rtr.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String token;

  private String messsage;

  private  User user;

  public AuthenticationResponse(String messageResponse) {
    this.messsage = messageResponse;
  }
}
