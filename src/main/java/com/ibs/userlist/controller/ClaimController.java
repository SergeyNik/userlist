package com.ibs.userlist.controller;

import com.ibs.userlist.dto.ClaimDto;
import com.ibs.userlist.model.Claim;
import com.ibs.userlist.service.ClaimService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<ClaimDto>> getAllClaims() {
        List<Claim> allClaims = claimService.getAll();
        List<ClaimDto> allClaimsDto =
                allClaims
                        .stream()
                        .map(source -> modelMapper.map(source, ClaimDto.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(allClaimsDto);
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

    @DeleteMapping
    public ResponseEntity<Void> deleteBook(@RequestParam("id") long id) {
        claimService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
