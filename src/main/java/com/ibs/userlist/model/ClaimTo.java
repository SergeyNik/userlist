package com.ibs.userlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "claim_to")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimTo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "to_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "claimTo")
    private Set<Claim> claims;
}
