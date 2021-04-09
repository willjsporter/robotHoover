package com.willjsporter.controller;

import com.willjsporter.model.HooverInput;
import com.willjsporter.model.HooverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HooverInputController {

    private HooverService hooverService = new HooverService();

    @PostMapping(path = "/sendInput")
    public ResponseEntity<String> sendHooverInput(@RequestBody HooverInput input) {
        return ResponseEntity.ok(hooverService.run(input).toJsonOutput());
    }
}