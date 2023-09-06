package pl.damiang19.github.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.damiang19.github.webclient.GithubRepositoryClient;
import pl.damiang19.github.webclient.dto.response.RepositoryResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final GithubRepositoryClient githubRepositoryClient;

    public List<RepositoryResponseDTO> getRepositories(String username) {
        return githubRepositoryClient.getRepositoriesForUser(username);
    }
}
