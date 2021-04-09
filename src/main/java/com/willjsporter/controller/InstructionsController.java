package com.willjsporter.controller;

import com.willjsporter.model.Instructions;
import com.willjsporter.model.InstructionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructionsController {

    private InstructionsService instructionsService = new InstructionsService();

    @PostMapping(path = "/sendInstructions")
    public ResponseEntity<String> sendInstructions(@RequestBody Instructions instructions) {
        return ResponseEntity.ok(instructionsService.run(instructions).toJsonOutput());
    }
}