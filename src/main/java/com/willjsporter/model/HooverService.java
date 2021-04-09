package com.willjsporter.model;

import org.springframework.stereotype.Service;

@Service
public class HooverService {

    public HooverOutput run(HooverInput hooverInput) {
        int numberPatchesCleaned = (int) hooverInput.getPatches().stream()
            .filter(patch -> patch.equals(hooverInput.getCoords()))
            .count();
        return new HooverOutput(hooverInput.getCoords(), numberPatchesCleaned);
    }
}
