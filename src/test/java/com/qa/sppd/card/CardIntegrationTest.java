package com.qa.sppd.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
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
@Sql(scripts={"classpath:card-schema.sql", "classpath:card-data.sql"},
        executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CardIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void create_TEST() throws Exception {
        Card requestBody = new Card("The Amazingly Randy", "fantasy", "ranged", "epic", 4);
        String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody);

        RequestBuilder request = post("/card/create").contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyAsJSON);

        Card responseBody = new Card(2, "The Amazingly Randy", "fantasy", "ranged", "epic", 4);
        String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);

        ResultMatcher checkStatus = status().isCreated();
        ResultMatcher checkBody = content().json(responseBodyAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void getAll_TEST() throws Exception {
        RequestBuilder request = get("/card/getAll");

        ResultMatcher checkStatus = status().isOk();

        Card tempCard = new Card(1, "Visitors", "sci-fi", "ranged", "rare", 3);
        List<Card> responseBody = List.of(tempCard);

        String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);
        ResultMatcher checkBody = content().json(responseBodyAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void getCardByIndex_TEST() throws Exception {
        final String responseBody = this.mapper.writeValueAsString(
                new Card(1,  "Visitors", "sci-fi", "ranged", "rare", 3));
        this.mvc.perform(get("/card/get/1")).andExpect(status().isOk()).andExpect(content().json(responseBody));
    }

    @Test
    void cardNotFound_TEST() throws Exception {

    }

}
