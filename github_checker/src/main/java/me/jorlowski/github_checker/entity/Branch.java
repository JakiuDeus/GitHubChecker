package me.jorlowski.github_checker.entity;

public class Branch {
	private String name;
	private String lastCommitSha;
	
	public Branch(String name, String lcs) {
		this.name = name;
		lastCommitSha = lcs;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSha() {
		return lastCommitSha;
	}
}
