package com.ibs.userlist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Инициатор")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimFromDto {

    @NotNull
    private Long id;

    @ApiModelProperty("Название")
    @NotBlank
    private String name;
}
