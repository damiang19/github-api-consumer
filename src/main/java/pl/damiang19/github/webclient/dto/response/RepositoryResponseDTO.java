package pl.damiang19.github.webclient.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RepositoryResponseDTO {
    private String owner;
    private String name;
    private List<BranchResponseDTO> branches;

}
