package com.games.gonbuy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.gonbuy.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameControllerTest {

    private MockMvc mvc;
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public GameControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    public void testStartGame() throws Exception {
        MockHttpServletResponse resp = mvc.perform(get("/game/start?n=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(200);
    }

    @Test
    public void testStartGameThrowException() throws Exception {
        assertThatThrownBy(() -> mvc.perform(get("/game/start?n=7")))
                .message().endsWith("Number of players should be from 2 to 6.");
    }

    @Test
    public void testStartGameThrowException2() throws Exception {
        assertThatThrownBy(() -> mvc.perform(get("/game/start?n=1")))
                .message().endsWith("Number of players should be from 2 to 6.");
    }

    @Test
    public void testGetGameById() throws Exception {
        testStartGame();
        MockHttpServletResponse resp = mvc.perform(get("/game/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(200);
    }

    @Test
    public void testGetAllGames() throws Exception {
        testStartGame();
        testStartGame();
        testStartGame();
        MockHttpServletResponse resp = mvc.perform(get("/game")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        String bodyAsStr = resp.getContentAsString();
        List<Game> responseBody = mapper.readValue(bodyAsStr, List.class);
        assertThat(resp.getStatus()).isEqualTo(200);
        assertThat(responseBody.size()).isEqualTo(3);
    }

    @Test
    public void testMovePlayer() throws Exception {
        testStartGame();
        MockHttpServletResponse resp = mvc.perform(get("/game/1/move/Player1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(200);
    }

}
