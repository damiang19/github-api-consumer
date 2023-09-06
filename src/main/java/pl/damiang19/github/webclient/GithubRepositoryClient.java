package pl.damiang19.github.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.damiang19.github.controller.errors.UserNotFoundException;
import pl.damiang19.github.webclient.dto.request.BranchDTO;
import pl.damiang19.github.webclient.dto.request.RepositoryDTO;
import pl.damiang19.github.webclient.dto.response.BranchResponseDTO;
import pl.damiang19.github.webclient.dto.response.RepositoryResponseDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GithubRepositoryClient {
    private static final String GITHUB_URL = "https://api.github.com/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<RepositoryResponseDTO> getRepositoriesForUser(String userName) {
        List<RepositoryDTO> listOfRepositories = getUserRepositories(userName);
        listOfRepositories.stream().filter(repository -> !repository.getFork()).forEach(repositoryDTO -> {
            List<BranchDTO> listOf = getRepositoryBranches(userName, repositoryDTO.getName());
            repositoryDTO.setBranches(listOf);
        });
        return convertRepositoryRequestToResponse(listOfRepositories);
    }

    private List<RepositoryResponseDTO> convertRepositoryRequestToResponse(List<RepositoryDTO> listOfRepositories) {
        List<RepositoryResponseDTO> repositoryResponseList = new ArrayList<>();
        listOfRepositories.forEach(position -> {
            repositoryResponseList.add(RepositoryResponseDTO
                    .builder()
                    .owner(position.getOwner().getLogin())
                    .name(position.getName())
                    .branches(convertBranchRequestToResponse(position.getBranches()))
                    .build());
        });
        return repositoryResponseList;
    }

    private List<BranchResponseDTO> convertBranchRequestToResponse(List<BranchDTO> branchDTOList) {
        List<BranchResponseDTO> listOfBranches = new ArrayList<>();
        branchDTOList.forEach(position -> {
            listOfBranches.add(BranchResponseDTO
                    .builder()
                    .name(position.getName())
                    .sha(position.getCommit().getSha())
                    .build());
        });
        return listOfBranches;
    }

    private List<RepositoryDTO> getUserRepositories(String username) {
        return throwExceptionIfUserDoesNotExist(username);
    }

    private List<RepositoryDTO> throwExceptionIfUserDoesNotExist(String username) {
        try {
            return createGithubQuery("users/{username}/repos", RepositoryDTO[].class, username);
        } catch (RuntimeException runtimeException) {
            throw new UserNotFoundException("User with username " + username + " login not found");
        }
    }

    private List<BranchDTO> getRepositoryBranches(String username, String repositoryName) {
        return createGithubQuery("repos/{username}/{repositoryName}/branches", BranchDTO[].class, username, repositoryName);
    }

    private <T> List<T> createGithubQuery(String url, Class<T[]> responseType, Object... params) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(GITHUB_URL + url, responseType, params)))
                .collect(Collectors.toList());
    }
}
