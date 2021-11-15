package com.qa.sppd.card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sppd.persistence.domain.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts={"classpath:card-schema.sql", "classpath:card-data.sql"},
        executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
    void getByIndexNotFound_TEST() throws Exception {
        int tempIndex = 999999;
        this.mvc.perform(get("/card/get/" + tempIndex))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No card exists at index: " + tempIndex));
    }

    @Test
    void replaceByIndex_TEST() throws Exception {
        final String responseBody = this.mapper.writeValueAsString(
                new Card(1, "Mr Mackey", "neutral", "fighter", "common", 4));

        RequestBuilder request = put("/card/replace/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(responseBody);

        ResultMatcher checkStatus = status().isAccepted();
        ResultMatcher checkBody = content().json(responseBody);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void replaceByIndexNotFound_TEST() throws Exception {
        int tempIndex = 999999;

        final String requestBody = this.mapper.writeValueAsString(
                new Card(1, "Mr Mackey", "neutral", "fighter", "common", 4));
        RequestBuilder request = put("/card/replace/" + tempIndex)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        this.mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No card exists at index: " + tempIndex));
    }

    @Test
    void removeByIndex_TEST() throws Exception {
        this.mvc.perform(delete("/card/remove/1")).andExpect(status().isNoContent());
    }

}
