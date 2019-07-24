package com.ibs.userlist.service.impl;

import com.ibs.userlist.exceptions.NotFoundException;
import com.ibs.userlist.model.Claim;
import com.ibs.userlist.repository.ClaimRepository;
import com.ibs.userlist.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public List<Claim> getAll() {
        return claimRepository.findAll();
    }

    @Override
    public Claim getById(Long id) {
        return claimRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void create(Claim claim) {

    }

    @Override
    public Claim update(Claim current, Claim updated) {

        current.getClaimTo().setId(updated.getClaimTo().getId());

        return claimRepository.save(current);
    }

    @Override
    public void deleteById(Long id) {
        claimRepository.deleteById(id);
    }
}
