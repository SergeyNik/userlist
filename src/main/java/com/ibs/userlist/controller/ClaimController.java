package com.ibs.userlist.controller;

import com.ibs.userlist.dto.ClaimDto;
import com.ibs.userlist.model.Claim;
import com.ibs.userlist.service.ClaimService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value="Claim Controller")
@RestController
@RequestMapping("claim")
public class ClaimController {

    private final ClaimService claimService;

    private final ModelMapper modelMapper;

    @Autowired
    public ClaimController(ClaimService claimService, ModelMapper modelMapper) {
        this.claimService = claimService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<List<ClaimDto>> getAllClaims(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        List<Claim> claimsOnPage = claimService.getClaimsByCount(page, size);
        List<ClaimDto> claimsDto =
                claimsOnPage
                        .stream()
                        .map(source -> modelMapper.map(source, ClaimDto.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(claimsDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClaimDto> getClaim(@PathVariable long id) {
        Claim claim = claimService.getById(id);
        return ResponseEntity.ok(modelMapper.map(claim, ClaimDto.class));
    }

    @PostMapping
    public ResponseEntity<ClaimDto> create(@Valid @RequestBody ClaimDto claimDto) {

        Claim claim = claimService.create(modelMapper.map(claimDto, Claim.class));
        return ResponseEntity.ok(modelMapper.map(claim, ClaimDto.class));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClaimDto> update(@PathVariable long id,
                                           @Valid @RequestBody ClaimDto claimDto) {

        Claim updatedClaim = claimService.update(id, modelMapper.map(claimDto, Claim.class));
        return ResponseEntity.ok(modelMapper.map(updatedClaim, ClaimDto.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        claimService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
