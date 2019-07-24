package com.ibs.userlist.controller;

import com.ibs.userlist.exceptions.NotFoundException;
import com.ibs.userlist.model.Claim;
import com.ibs.userlist.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("claim")
public class ClaimController {

    private final ClaimService claimService;

    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public List<Claim> claim() {
        return claimService.getAllClaims();
    }

    @GetMapping("{id}")
    public Claim getClaim(@PathVariable String id) {
        return Optional.of(claimService.getAllClaims().get(Integer.parseInt(id))).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void create(@RequestBody Claim claim) {
        claimService.create(claim);
    }

    @PutMapping
    public void update(@PathVariable String id, @RequestBody Claim claim) {
        Claim dbClaim = getClaim(id);
        claimService.update(claim);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Integer id) {
        claimService.deleteById(id);
    }
}
