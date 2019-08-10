package com.ibs.userlist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Получатель")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimToDto {

    @NotNull
    private Long id;

    @ApiModelProperty(value = "Название")
    @NotBlank
    private String name;
}
