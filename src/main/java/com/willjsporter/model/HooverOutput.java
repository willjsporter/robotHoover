package com.willjsporter.model;

import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@ToString
public class HooverOutput {

    @Id
    @SequenceGenerator(name = "hoover_output_sequence", sequenceName = "hoover_output_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hoover_output_sequence")
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Pair coords;
    private int patches;

    public HooverOutput(Pair coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public HooverOutput() {}

    public String toJsonOutput() {
        return String.format(
            "{\n" +
            "  \"coords\" : [%d, %d],\n" +
            "  \"patches\" : %d\n" +
            "}",
            coords.getX(), coords.getY(), patches
        );
    }

    public Pair getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HooverOutput hooverOutput = (HooverOutput) o;
        return getPatches() == hooverOutput.getPatches() &&
            Objects.equals(getCoords(), hooverOutput.getCoords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoords(), getPatches());
    }
}
