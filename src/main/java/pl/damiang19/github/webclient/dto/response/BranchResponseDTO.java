package pl.damiang19.github.webclient.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BranchResponseDTO {
    private String name;
    private String sha;
}
