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
        return new HooverOutput(move(hooverInput.getInstructions(), hooverInput.getCoords(), hooverInput.getRoomSize()), numberPatchesCleaned);
    }

    private Pair move(String instructions, Pair position, Pair roomSize) {
        if(instructions.length() == 0) {
            return position;
        } else {
            return move(instructions.substring(1), directionMap.get(instructions.charAt(0)).moveInDirection(position, roomSize), roomSize);
        }
    }

    //do out of bounds check - should stay still in this case AAA
    //do proper counting
    //do no duplicate counting
    //do errors for bad input in controller (invalid start position, negative numbers, not a HooverInput object)
    //persist input and output to db

}
