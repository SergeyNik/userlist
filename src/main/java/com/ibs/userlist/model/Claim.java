package com.ibs.userlist.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    private int id;
    private String first;
    private String last;
    private boolean status;
}
