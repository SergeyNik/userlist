package com.ibs.userlist.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimFromDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
}
