package com.ibs.userlist.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "claim")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    @Column(name = "claim_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "claim_to_id")
    private ClaimTo claimTo;

    @ManyToOne
    @JoinColumn(name = "claim_from_id")
    private ClaimFrom claimFrom;

    @ManyToOne
    @JoinColumn(name = "claim_status_id")
    private ClaimStatus claimStatus;
}
