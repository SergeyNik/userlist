package com.ibs.userlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserlistApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getClaimRest() throws Exception {
        MediaType halJson = MediaType.parseMediaType("application/hal+json;character=UTF-8");
        this.mvc
                .perform(get("/claim"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(halJson))
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
//                    Assert.isTrue(contentAsString.);
                });
    }

}
