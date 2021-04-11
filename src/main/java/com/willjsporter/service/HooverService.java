package com.willjsporter.service;

import com.willjsporter.model.HooverInput;
import com.willjsporter.model.HooverOutput;
import com.willjsporter.model.Pair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class HooverService {

    private Map<Character, Direction> directionMap = Map.of(
        'N', Direction.N,
        'E', Direction.E,
        'S', Direction.S,
        'W', Direction.W
    );

    public HooverOutput run(HooverInput hooverInput) {
        final HashSet<Pair> patchesCleaned = new HashSet<>();
        return move(hooverInput.getInstructions(), hooverInput.getCoords(), hooverInput, patchesCleaned);
    }

    private HooverOutput move(String instructions, Pair position, HooverInput input, Set<Pair> patchesCleaned) {
        if(input.getPatches().contains(position)) {
            patchesCleaned.add(position);
        }
        if(instructions.length() == 0) {
            return new HooverOutput(position, patchesCleaned.size());
        } else {
            return move(instructions.substring(1), directionMap.get(instructions.charAt(0)).moveInDirection(position, input.getRoomSize()), input, patchesCleaned);
        }
    }

    //do out of bounds check - should stay still in this case AAA
    //do proper counting - AAA
    //do no duplicate counting - AAA
    //do errors for bad input in controller (invalid start position, negative numbers, not a HooverInput object)
    //persist input and output to db

}
