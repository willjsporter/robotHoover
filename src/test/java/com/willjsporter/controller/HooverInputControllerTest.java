package com.willjsporter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HooverInputControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String TEST_HOOVER_INPUT_STRING = "{" +
        "  \"roomSize\" : [5, 5]," +
        "  \"coords\" : [1, 2]," +
        "  \"patches\" : [" +
        "    [1, 0]," +
        "    [2, 2]," +
        "    [2, 3]" +
        "  ]," +
        "  \"instructions\" : \"NNESEESWNWW\"" +
        "}";

    @Test
    public void controllerShouldReturnHooverOutputJsonWhenSentHooverInput() throws Exception {
        this.mockMvc.perform(post("/sendInput").contentType(MediaType.APPLICATION_JSON).content(TEST_HOOVER_INPUT_STRING))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.coords[0]", is(1)))
            .andExpect(jsonPath("$.coords[1]", is(3)))
            .andExpect(jsonPath("$.patches", is(1)));
    }
}