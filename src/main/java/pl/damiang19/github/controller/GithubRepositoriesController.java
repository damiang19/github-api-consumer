package pl.damiang19.github.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.damiang19.github.webclient.GithubRepositoryClient;

@RestController
@RequiredArgsConstructor
public class GithubRepositoriesController {

    private final GithubRepositoryClient githubRepositoryClient;

    @GetMapping("/{username}/repositories")
    public ResponseEntity<?> getRepositoriesForUser(@PathVariable String username) {
        return ResponseEntity.ok(githubRepositoryClient.getUserRepositories(username));
    }
}
