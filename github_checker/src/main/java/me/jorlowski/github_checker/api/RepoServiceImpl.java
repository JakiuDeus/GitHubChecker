package me.jorlowski.github_checker.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import me.jorlowski.github_checker.entity.Branch;
import me.jorlowski.github_checker.entity.Repo;
import me.jorlowski.github_checker.tools.Utils;
@Service
public class RepoServiceImpl implements RepoService {
	
	@Override
	public List<Repo> getUserRepos(String username) throws IOException {
		String url = "https://api.github.com/users/" + username + "/repos";
		String jsonRepos = Utils.fetch(url);
		if (jsonRepos == null) {
			return null;
		}
		
		List<Repo> reposList = new ArrayList<>();
		List<Branch> branches;
		
		JsonNode nodes = Utils.parse(jsonRepos);
		for(JsonNode n : nodes) {
			if (n.get("fork").asBoolean() == false) {
				branches = getBranches(n.get("branches_url").asText());
				Repo obj = new Repo(
						n.get("name").asText()
						, n.get("owner").get("login").asText()
						, branches
				);
				reposList.add(obj);
			}
		}
		return reposList;
	}

	private List<Branch> getBranches(String url) throws IOException {
		url = url.split("\\{")[0];
		String jsonBranches = Utils.fetch(url);
		JsonNode nodes = Utils.parse(jsonBranches);
		
		List<Branch> branches = new ArrayList<>();
		
		for(JsonNode n : nodes) {
			Branch obj = new Branch(n.get("name").asText(), n.get("commit").get("sha").asText());
			branches.add(obj);
		}
		return branches;
	}

}
