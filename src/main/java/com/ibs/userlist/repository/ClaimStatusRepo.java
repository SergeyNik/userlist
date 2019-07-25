package com.ibs.userlist.repository;

import com.ibs.userlist.model.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimStatusRepo extends JpaRepository<ClaimStatus, Long> {

}
