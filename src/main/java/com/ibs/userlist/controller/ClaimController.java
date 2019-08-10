package com.ibs.userlist.controller;

import com.ibs.userlist.dto.ClaimDto;
import com.ibs.userlist.model.Claim;
import com.ibs.userlist.service.ClaimService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@Api(value="Контроллер для управления заявками")
@RequiredArgsConstructor
@RestController
@RequestMapping("claim")
public class ClaimController {

    private final ClaimService claimService;

    private final ModelMapper modelMapper;

    @ApiOperation(value = "Получить заявки по страницам", response = ClaimDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
    })
    @GetMapping
    public ResponseEntity<List<ClaimDto>> getAllClaims(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        val claimsOnPage = claimService.getClaimsByCount(page, size);
        val claimsDto =
                claimsOnPage
                        .stream()
                        .map(source -> modelMapper.map(source, ClaimDto.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(claimsDto);
    }


    @ApiOperation(value = "Получить заявку по идентификатору", response = ClaimDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Заявка получена"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClaimDto> getClaim(
            @ApiParam(value = "Id заявки", required = true)
            @PathVariable long id) {
        val claim = claimService.getById(id);
        return ResponseEntity.ok(modelMapper.map(claim, ClaimDto.class));
    }

    @ApiOperation(value = "Создать заявку", response = ClaimDto.class)
    @PostMapping
    public ResponseEntity<ClaimDto> create(
            @ApiParam(value = "Данные новой заявки", required = true) @Valid @RequestBody ClaimDto claimDto) {

        val claim = claimService.create(modelMapper.map(claimDto, Claim.class));
        return ResponseEntity.ok(modelMapper.map(claim, ClaimDto.class));
    }

    @ApiOperation(value = "Обновить заявку", response = ClaimDto.class)
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClaimDto> update(
            @ApiParam(value = "Id заявки", required = true)
            @PathVariable long id,
            @ApiParam(value = "Данные для обновления заявки", required = true)
            @Valid @RequestBody ClaimDto claimDto) {

        val updatedClaim = claimService.update(id, modelMapper.map(claimDto, Claim.class));
        return ResponseEntity.ok(modelMapper.map(updatedClaim, ClaimDto.class));
    }

    @ApiOperation(value = "Удалить заявку")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBook(
            @ApiParam(value = "Id заявки", required = true) @PathVariable long id) {
        claimService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
