package com.willjsporter.controller;

import com.willjsporter.model.Instructions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructionsController {


    @PostMapping(path = "/sendInstructions")
    @ResponseBody
    public ResponseEntity<Instructions> sendInstructions(@RequestBody Instructions instructions) {
        return ResponseEntity.ok(instructions);
    }
}