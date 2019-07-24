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
    public ResponseEntity<ClaimDto> getClaim(@PathVariable Long id) {
        Claim claim = claimService.getById(id);
        return ResponseEntity.ok(modelMapper.map(claim, ClaimDto.class));
    }

    @PostMapping(params = {"id"})
    public void create(@RequestParam("id") Long id,
                       @Valid @RequestBody ClaimDto claimDto) {

//        claimService.create(claim);
    }

    @PutMapping
    public ResponseEntity<ClaimDto> update(@PathVariable Long id,
                                           @Valid @RequestBody ClaimDto claimDto) {

        Claim claim = claimService.getById(id);
        Claim updatedClaim = claimService.update(claim, modelMapper.map(claimDto, Claim.class));

        return ResponseEntity.ok(modelMapper.map(updatedClaim, ClaimDto.class));
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        claimService.deleteById(id);
    }

//    @DeleteMapping
//    public ResponseEntity<Void> delete(@RequestParam("id") long id) {
//        articleService.markArticleAsDeleted(id);
//
//        return ResponseEntity.ok().build();
//    }
}
