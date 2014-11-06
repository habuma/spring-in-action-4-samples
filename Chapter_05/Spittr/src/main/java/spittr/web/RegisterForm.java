package spittr.web;

import spittr.Spitter;

public class RegisterForm {
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String passwordConfirm;
  private String email;
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getPasswordConfirm() {
    return passwordConfirm;
  }
  
  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Spitter toSpitter() {
    return new Spitter(username, password, firstName, lastName, email);
  }

}
