package spittr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Spitter {
	
	private Spitter() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="fullname")
	private String fullName;

	@Column(name="email")
	private String email;

	@Column(name="updateByEmail")
	private boolean updateByEmail;

	public Spitter(Long id, String username, String password, String fullName,
			String email, boolean updateByEmail) {
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
