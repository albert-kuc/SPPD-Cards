package com.qa.sppd.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
public class CardIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void Create_TEST() throws Exception {
        Card requestBody = new Card("The Amazingly Randy", "fantasy", "ranged", "epic", 4);
        String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody);

        RequestBuilder request = post("/card/create").contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyAsJSON);

        Card responseBody = new Card(1, "The Amazingly Randy", "fantasy", "ranged", "epic", 4);
        String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);

        ResultMatcher checkStatus = status().isCreated();
        ResultMatcher checkBody = content().json(responseBodyAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void GetAll_TEST() throws Exception {
        RequestBuilder request = get("/card/getAll");

        ResultMatcher checkStatus = status().isOk();

        Card tempCard = new Card(1, "The Amazingly Randy", "fantasy", "ranged", "epic", 4);
        List<Card> responseBody = List.of(tempCard);

        String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);
        ResultMatcher checkBody = content().json(responseBodyAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
    }

}
