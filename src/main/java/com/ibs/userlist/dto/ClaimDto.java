package com.ibs.userlist.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimDto {
    private Long id;
    private String name;
    private ClaimToDto claimTo;
    private ClaimFromDto claimFrom;
    private ClaimStatusDto claimStatus;
}