package com.ibs.userlist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Статус")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimStatusDto {

    @NotNull
    private Long id;

    @ApiModelProperty("Название")
    @NotBlank
    private String name;
}
