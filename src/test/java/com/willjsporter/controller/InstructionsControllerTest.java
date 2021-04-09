package com.willjsporter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class InstructionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String TEST_INSTRUCTIONS_STRING = "{" +
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
    public void controllerShouldDeserializeInstructionsStringToInstructionsObject() throws Exception {
        this.mockMvc.perform(post("/sendInstructions").contentType(MediaType.APPLICATION_JSON).content(TEST_INSTRUCTIONS_STRING))
            .andExpect(status().isOk());
    }
}