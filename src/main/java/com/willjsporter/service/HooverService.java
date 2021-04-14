package com.willjsporter.service;

import com.willjsporter.model.HooverInput;
import com.willjsporter.model.HooverOutput;
import com.willjsporter.model.Pair;
import com.willjsporter.repository.HooverInputRepository;
import com.willjsporter.repository.HooverOutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class HooverService {

    @Autowired
    private HooverInputRepository hooverInputRepository;
    @Autowired
    private HooverOutputRepository hooverOutputRepository;

    public HooverService(HooverInputRepository hooverInputRepository, HooverOutputRepository hooverOutputRepository) {
        this.hooverInputRepository = hooverInputRepository;
        this.hooverOutputRepository = hooverOutputRepository;
    }

    private final Map<Character, Direction> directionMap = Map.of(
        'N', Direction.N,
        'E', Direction.E,
        'S', Direction.S,
        'W', Direction.W
    );

    public HooverOutput run(HooverInput hooverInput) {
        hooverInputRepository.save(hooverInput);
        final HashSet<Pair> patchesCleaned = new HashSet<>();
        HooverOutput hooverOutput = move(hooverInput.getInstructions(), hooverInput.getCoords(), hooverInput, patchesCleaned);
        hooverOutputRepository.save(hooverOutput);
        return hooverOutput;
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
}
