package com.ibs.userlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "claim_from")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimFrom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "from_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "claimFrom")
    private Set<Claim> claims;

}
