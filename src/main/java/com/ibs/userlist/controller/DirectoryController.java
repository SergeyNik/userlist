package com.ibs.userlist.controller;

import com.ibs.userlist.dto.ClaimDto;
import com.ibs.userlist.dto.ClaimFromDto;
import com.ibs.userlist.dto.ClaimStatusDto;
import com.ibs.userlist.dto.ClaimToDto;
import com.ibs.userlist.model.ClaimFrom;
import com.ibs.userlist.model.ClaimStatus;
import com.ibs.userlist.model.ClaimTo;
import com.ibs.userlist.service.ClaimService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("list")
public class DirectoryController {

    private final ClaimService claimService;

    private final ModelMapper modelMapper;

    @Autowired
    public DirectoryController(ClaimService claimService, ModelMapper modelMapper) {
        this.claimService = claimService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/to")
    public ResponseEntity<List<ClaimToDto>> getAllClaimsTo() {
        List<ClaimTo> listTo = claimService.getAllClaimTo();
        List<ClaimToDto> listToDto =
                listTo
                        .stream()
                        .map(source -> modelMapper.map(source, ClaimToDto.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(listToDto);
    }

    @GetMapping("/from")
    public ResponseEntity<List<ClaimFromDto>> getAllClaimsFrom() {
        List<ClaimFrom> listFrom = claimService.getAllClaimFrom();
        List<ClaimFromDto> listFromDto =
                listFrom
                        .stream()
                        .map(source -> modelMapper.map(source, ClaimFromDto.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(listFromDto);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ClaimStatusDto>> getAllClaimsStatus() {
        List<ClaimStatus> listStatus = claimService.getAllClaimStatus();
        List<ClaimStatusDto> listStatusDto =
                listStatus
                        .stream()
                        .map(source -> modelMapper.map(source, ClaimStatusDto.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(listStatusDto);
    }
}
