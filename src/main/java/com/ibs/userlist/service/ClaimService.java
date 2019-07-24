package com.ibs.userlist.service;

import com.ibs.userlist.model.Claim;

import java.util.List;

public interface ClaimService {

    List<Claim> getAll();

    void deleteById(Long id);

    Claim update(Claim current, Claim updated);

    void create(Claim claim);

    Claim getById(Long id);
}
