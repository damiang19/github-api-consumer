package pl.damiang19.github.webclient.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RepositoryDTO {

    private OwnerDTO owner;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean fork;
    private List<BranchDTO> branches;
}
