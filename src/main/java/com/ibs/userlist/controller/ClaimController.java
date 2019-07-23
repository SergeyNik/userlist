package com.ibs.userlist.controller;

import com.ibs.userlist.exceptions.NotFoundException;
import com.ibs.userlist.model.Claim;
import jdk.nashorn.internal.runtime.options.Options;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("claim")
public class ClaimController {

    private List<Claim> claimList = Arrays.asList(
            new Claim(1, "Jack", "Frank", true),
            new Claim(2, "John", "Mike", false),
            new Claim(3, "Elton", "Sunny", true),
            new Claim(4, "Razor", "Rich", false)
    );

    @GetMapping
    public List<Claim> claim() {
        return claimList;
    }

    @GetMapping("{id}")
    public Claim getOne(@PathVariable int id) {
        return Optional.of(claimList.get(id)).orElseThrow(NotFoundException::new);
    }

}
