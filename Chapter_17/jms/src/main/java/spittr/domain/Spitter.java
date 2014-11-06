package spittr.domain;

public class Spitter {
  
  private Long id;
  private String username;
  private String password;
  private String fullName;
  private String email;
  private boolean updateByEmail;

  public Spitter(Long id, String username, String password, String fullName, String email, boolean updateByEmail) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.updateByEmail = updateByEmail;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public boolean isUpdateByEmail() {
    return updateByEmail;
  }

}
