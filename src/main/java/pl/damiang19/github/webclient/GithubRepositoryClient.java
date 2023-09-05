package pl.damiang19.github.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.damiang19.github.controller.errors.UserNotFoundException;
import pl.damiang19.github.webclient.dto.BranchDTO;
import pl.damiang19.github.webclient.dto.RepositoryDTO;

import java.util.Arrays;
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
        List<RepositoryDTO> list = createGithubQuery("users/{username}/repos", RepositoryDTO[].class, username);
        throwExceptionIfListIsEmpty(list, username);
        return list;
    }

    private void throwExceptionIfListIsEmpty(List<RepositoryDTO> list, String username) {
        if (list.isEmpty()) {
            throw new UserNotFoundException("User with username " + username + " login not found");
        }
    }

    public List<BranchDTO> getRepositoryBranches(String username, String repositoryName) {
        return createGithubQuery("repos/{username}/chat/branches", BranchDTO[].class, username, repositoryName);
    }

    public <T> List<T> createGithubQuery(String url, Class<T[]> responseType, Object... params) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(GITHUB_URL + url, responseType, params)))
                .collect(Collectors.toList());
    }
}
