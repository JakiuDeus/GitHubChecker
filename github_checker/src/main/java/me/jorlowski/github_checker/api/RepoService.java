package me.jorlowski.github_checker.api;

import java.io.IOException;
import java.util.List;

import me.jorlowski.github_checker.entity.Repo;

public interface RepoService {
	List<Repo> getUserRepos(String username) throws IOException;
}
