package com.willjsporter.service;

import com.willjsporter.model.HooverInput;
import com.willjsporter.model.HooverOutput;
import com.willjsporter.model.Pair;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HooverService {

    private Map<Character, Direction> directionMap = Map.of(
        'N', Direction.N,
        'E', Direction.E,
        'S', Direction.S,
        'W', Direction.W
    );

    public HooverOutput run(HooverInput hooverInput) {
        int numberPatchesCleaned = (int) hooverInput.getPatches().stream()
            .filter(patch -> patch.equals(hooverInput.getCoords()))
            .count();
        return new HooverOutput(move(hooverInput.getInstructions(), hooverInput.getCoords()), numberPatchesCleaned);
    }

    private Pair move(String instructions, Pair position) {
        if(instructions.length() == 0) {
            return position;
        } else {
            return move(instructions.substring(1), directionMap.get(instructions.charAt(0)).move(position));
        }
    }
}
