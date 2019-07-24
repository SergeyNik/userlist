package com.ibs.userlist.service.impl;

import com.ibs.userlist.model.Claim;
import com.ibs.userlist.repository.ClaimRepository;
import com.ibs.userlist.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    @Transactional
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(Claim claim) {

    }

    @Override
    public void create(Claim claim) {

    }
}
