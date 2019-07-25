package com.ibs.userlist.service;

import com.ibs.userlist.model.Claim;
import com.ibs.userlist.model.ClaimFrom;
import com.ibs.userlist.model.ClaimStatus;
import com.ibs.userlist.model.ClaimTo;

import java.util.List;

public interface ClaimService {

    List<Claim> getAll();

    void deleteById(long id);

    Claim update(long id, Claim updated);

    Claim create(Claim newClaim);

    Claim getById(long id);

    List<ClaimTo> getAllClaimTo();

    List<ClaimFrom> getAllClaimFrom();

    List<ClaimStatus> getAllClaimStatus();
}
