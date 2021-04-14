package com.willjsporter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HooverInputControllerTest {

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

    @Test
    public void whenObjectSentIsNotHooverInputObject_ControllerShouldReturn422Status() throws Exception {
        final String requestBody = "{ \"hello\" : 1, \"world\" : 2 }";
        this.mockMvc.perform(post("/sendInput").contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$", is("Error: Hoover input instructions could not be read")));
    }

    @Test
    public void whenSentInvalidHooverInputData_ControllerShouldReturn500Status() throws Exception {
        final String TEST_HOOVER_INPUT_STRING = "{" +
            "  \"roomSize\" : [5, 5]," +
            "  \"coords\" : [-1, 2]," +
            "  \"patches\" : [" +
            "    [1, 0]" +
            "  ]," +
            "  \"instructions\" : \"NNESEESWNWW\"" +
            "}";

        this.mockMvc
            .perform(post("/sendInput").contentType(MediaType.APPLICATION_JSON).content(TEST_HOOVER_INPUT_STRING))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$", is("Invalid starting point: starting point must be within specified roomSize but was [-1, 2].")));
    }
}