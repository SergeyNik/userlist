package com.ibs.userlist.repository;

import com.ibs.userlist.model.ClaimFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimFromRepo extends JpaRepository<ClaimFrom, Long> {

}
