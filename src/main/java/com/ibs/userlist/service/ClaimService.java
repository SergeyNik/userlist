package com.ibs.userlist.service;

import com.ibs.userlist.model.Claim;

public interface ClaimService {
    void deleteById(Integer id);

    void update(Claim claim);

    void create(Claim claim);
}
