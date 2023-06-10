package me.jorlowski.github_checker.entity;

import java.util.List;

public class Repo {
	private String name;
	private String login;
	private List<Branch> branches;
	
	public Repo(String name, String login, List<Branch> branches) {
		this.name = name;
		this.login = login;
		this.branches = branches;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public List<Branch> getBranches() {
		return branches;
	}
}
