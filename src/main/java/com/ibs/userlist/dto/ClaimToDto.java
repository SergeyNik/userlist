package com.ibs.userlist.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimToDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
}
