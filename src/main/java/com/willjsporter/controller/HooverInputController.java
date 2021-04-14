package com.willjsporter.controller;

import com.willjsporter.model.HooverInput;
import com.willjsporter.service.HooverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HooverInputController {

    @Autowired
    private HooverService hooverService;

    @PostMapping(path = "/sendInput")
    public ResponseEntity<String> sendHooverInput(@RequestBody HooverInput input) {
        return ResponseEntity.ok(hooverService.run(input).toJsonOutput());
    }
}