package com.willjsporter.model;

import java.util.List;
import java.util.Objects;

public class HooverInput {

    private Pair roomSize;
    private Pair coords;
    private List<Pair> patches;
    private String instructions;

    public HooverInput(Pair roomSize, Pair coords, List<Pair> patches, String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

    public Pair getRoomSize() {
        return roomSize;
    }

    public Pair getCoords() {
        return coords;
    }

    public List<Pair> getPatches() {
        return patches;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HooverInput that = (HooverInput) o;
        return getRoomSize().equals(that.getRoomSize()) &&
            Objects.equals(getCoords(), that.getCoords()) &&
            Objects.equals(getPatches(), that.getPatches()) &&
            Objects.equals(getInstructions(), that.getInstructions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomSize(), getCoords(), getPatches(), getInstructions());
    }
}
