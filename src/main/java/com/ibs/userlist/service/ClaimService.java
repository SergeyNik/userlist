package com.ibs.userlist.service;

import com.ibs.userlist.model.Claim;

import java.util.List;

public interface ClaimService {

    List<Claim> getAllClaims();

    void deleteById(Integer id);

    void update(Claim claim);

    void create(Claim claim);
}
