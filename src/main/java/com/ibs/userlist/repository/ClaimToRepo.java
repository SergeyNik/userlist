package com.ibs.userlist.repository;

import com.ibs.userlist.model.ClaimTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimToRepo extends JpaRepository<ClaimTo, Long> {

}
