package pl.damiang19.github.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.damiang19.github.service.RepositoryService;
import pl.damiang19.github.webclient.dto.ExceptionDTO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class GithubRepositoriesController {

    private final RepositoryService repositoryService;

    @GetMapping("/{username}/repositories")
    public ResponseEntity<?> getRepositoriesForUser(@PathVariable String username, HttpServletRequest httpRequest)  {
        if(httpRequest.getHeader("accept").equals("application/xml")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).contentType(MediaType.APPLICATION_JSON)
                    .body(new ExceptionDTO("Server can't handle application/xml accept header. Please use instead application/json", HttpStatus.NOT_ACCEPTABLE));
        }
        return ResponseEntity.ok(repositoryService.getRepositories(username));
    }
}
