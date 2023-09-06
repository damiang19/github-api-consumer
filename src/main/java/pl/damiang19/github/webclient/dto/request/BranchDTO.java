package pl.damiang19.github.webclient.dto.request;

import lombok.Getter;

@Getter
public class BranchDTO {
    private String name;
    private CommitDTO commit;
}
