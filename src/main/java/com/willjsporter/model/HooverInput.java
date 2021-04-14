package com.willjsporter.model;

import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table
@ToString
public class HooverInput {
    @Id
    @SequenceGenerator(name = "hoover_input_sequence", sequenceName = "hoover_input_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hoover_input_sequence")
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Pair roomSize;
    @OneToOne(cascade = CascadeType.ALL)
    private Pair coords;
    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Pair> patches;
    private String instructions;

    public HooverInput(Pair roomSize, Pair coords, Set<Pair> patches, String instructions) {
        this.roomSize = setRoomSizeOrThrow(roomSize);
        this.coords = validatePositionOrThrow(roomSize, coords, "starting point");
        this.patches = patches.stream()
            .map(patch -> validatePositionOrThrow(roomSize, patch, "patch location"))
            .collect(Collectors.toSet());
        this.instructions = instructions;
    }

    private Pair setRoomSizeOrThrow(Pair roomSize) {
        if(checkXYAreAtLeast(roomSize, 1)) {
            return roomSize;
        } else {
            throw new IllegalArgumentException("Invalid roomSize: x and y coordinates must be greater than 0.");
        }
    }

    private Pair validatePositionOrThrow(Pair roomSize, Pair coords, String identifier) {
        if(coords == null) {
            throw new IllegalArgumentException(String.format("Error: %s must be specified.", identifier));
        } else if(isPairInsideRoom(roomSize, coords)) {
            return coords;
        } else {
            throw new IllegalArgumentException(
                String.format(
                    "Invalid %s: %s must be within specified roomSize but was [%d, %d].",
                    identifier,
                    identifier,
                    coords.getX(),
                    coords.getY()
            ));
        }
    }

    private boolean isPairInsideRoom(Pair roomSize, Pair coords) {
        return checkXYAreAtLeast(coords, 0) && coords.getX() < roomSize.getX() && coords.getY() < roomSize.getY();
    }

    private boolean checkXYAreAtLeast(Pair pair, int minimumValue) {
        return pair.getX() >= minimumValue && pair.getY() >= minimumValue;
    }

    public Pair getRoomSize() {
        return roomSize;
    }

    public Pair getCoords() {
        return coords;
    }

    public Set<Pair> getPatches() {
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