package com.ibs.userlist.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private ClaimToDto claimTo;

    @NotNull
    private ClaimFromDto claimFrom;

    @NotNull
    private ClaimStatusDto claimStatus;
}