package pl.damiang19.github.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.damiang19.github.webclient.dto.BranchDTO;
import pl.damiang19.github.webclient.dto.RepositoryDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GithubRepositoryClient {

    private static final String GITHUB_URL = "https://api.github.com/";
    private final RestTemplate restTemplate = new RestTemplate();


    private List<?> getRepositoriesWithBranches(String userName, String repositoryName) {
        return null;
    }

    public List<RepositoryDTO> getUserRepositories(String username) {
        return createGithubQuery("users/{username}/repos",RepositoryDTO[].class, username );
    }

    public List<BranchDTO> getRepositoryBranches(String username, String repositoryName) {
        return createGithubQuery("repos/{username}/chat/branches", BranchDTO[].class, username, repositoryName);
    }

    public <T>  List<T> createGithubQuery(String url, Class<T[]> responseType, Object... params) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(GITHUB_URL + url, responseType, params)))
                .collect(Collectors.toList());
    }
}
