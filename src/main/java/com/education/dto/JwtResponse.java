package com.education.dto;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
 // private Long id;
 // private String username;
 // private String email;
 // private List<String> roles;

  public JwtResponse(String accessToken) {
    this.token = accessToken;
   // this.id = id;
  //  this.username = username;
   // this.email = email;
   // this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
 
}
