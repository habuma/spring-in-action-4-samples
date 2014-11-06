package spittr;

import java.util.Date;

public class Spittle {

	private Long id;
	private String user;
	private String message;
	private Date timestamp;

	public Spittle(String user, String message, Date timestamp) {
		this.user = user;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
}
