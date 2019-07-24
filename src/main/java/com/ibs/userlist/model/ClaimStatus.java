package com.ibs.userlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "claim_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "claimStatus")
    private Set<Claim> claims;
}
