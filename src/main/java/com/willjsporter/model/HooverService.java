package com.willjsporter.model;

import org.springframework.stereotype.Service;

@Service
public class HooverService {

    public HooverOutput run(HooverInput hooverInput) {
        return new HooverOutput(hooverInput.getCoords(), hooverInput.getPatches().size());
    }
}
