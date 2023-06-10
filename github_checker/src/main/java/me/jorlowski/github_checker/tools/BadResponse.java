package me.jorlowski.github_checker.tools;

public class BadResponse {

	private int status;
	private String message;
	
	public BadResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
}
