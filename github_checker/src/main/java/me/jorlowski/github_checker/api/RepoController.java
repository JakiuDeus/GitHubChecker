package me.jorlowski.github_checker.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import me.jorlowski.github_checker.entity.Repo;
import me.jorlowski.github_checker.tools.BadResponse;

@RestController
public class RepoController {
	
	@Autowired
	private RepoService repoService;
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUserRepos(@RequestHeader(HttpHeaders.ACCEPT) String lang
			, @PathVariable String username) throws IOException {
		
		if (lang.equals("application/xml")) {
			BadResponse response = new BadResponse(406, "Xml Format is not acceptable with this request");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
			return ResponseEntity.status(406).headers(headers).body(response);
		}
		
		List<Repo> repos = repoService.getUserRepos(username);
		
		if (repos == null) {
			BadResponse response = new BadResponse(404, "User was not found");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
			return ResponseEntity.status(404).headers(headers).body(response);
		}
		return new ResponseEntity<List<Repo>>(repos, HttpStatus.OK);
	}
}
