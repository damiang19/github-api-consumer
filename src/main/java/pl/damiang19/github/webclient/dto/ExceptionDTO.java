package pl.damiang19.github.webclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionDTO {
    private String message;
    private HttpStatus status;
}
