package com.ibs.userlist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Заявка")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDto {

    private Long id;

    @ApiModelProperty("Наименование")
    @NotBlank
    private String name;

    @ApiModelProperty("Кому назначена")
    @NotNull
    private ClaimToDto claimTo;

    @ApiModelProperty("Кто создал")
    @NotNull
    private ClaimFromDto claimFrom;

    @ApiModelProperty("Статус")
    @NotNull
    private ClaimStatusDto claimStatus;
}