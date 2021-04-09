package com.willjsporter.model;

import org.springframework.stereotype.Service;

@Service
public class InstructionsService {

    public HooverOutput run(Instructions instructions) {
        return new HooverOutput(instructions.getCoords(), instructions.getPatches().size());
    }
}
