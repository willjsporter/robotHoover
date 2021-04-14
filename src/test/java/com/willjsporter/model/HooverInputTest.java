package com.willjsporter.model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

class HooverInputTest {

    private HooverInput hooverInput;

    @Test
    public void hooverInputInitializeFailsWhenRoomSizeLessThan1() {
        try {
            hooverInput = new HooverInput(
                Pair.of(-1, 5),
                Pair.of(1, 2),
                Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
                "NESW"
            );
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Invalid roomSize: x and y coordinates must be greater than 0."));
        }
    }

    @Test
    public void hooverInputInitializeFailsWhenNegativeInitialCoords() {
        try {
            hooverInput = new HooverInput(
                Pair.of(5, 5),
                Pair.of(-1, 2),
                Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
                "NESW"
            );
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Invalid starting point: starting point must be within specified roomSize but was [-1, 2]."));
        }
    }

    @Test
    public void hooverInputInitializeFailsWhenInitialCoordsOutsideRoomSize() {
        try {
            hooverInput = new HooverInput(
                Pair.of(5, 5),
                Pair.of(4, 5),
                Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
                "NESW"
            );
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Invalid starting point: starting point must be within specified roomSize but was [4, 5]."));
        }
    }

    @Test
    public void hooverInputInitializeFailsWhenNullInitialCoords() {
        try {
            hooverInput = new HooverInput(
                Pair.of(5, 5),
                null,
                Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
                "NESW"
            );
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Error: starting point must be specified."));
        }
    }

    @Test
    public void hooverInputInitializeFailsWhenSomePatchIsOutsideRoom() {
        try {
            hooverInput = new HooverInput(
                Pair.of(5, 5),
                Pair.of(2, 2),
                Set.of(Pair.of(1, 2), Pair.of(2, 6), Pair.of(2, 3)),
                "NESW"
            );
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Invalid patch location: patch location must be within specified roomSize but was [2, 6]."));
        }
    }

    @Test
    public void hooverInputInitializeFailsWhenPatchesIsNull() {
        try {
            hooverInput = new HooverInput(
                Pair.of(5, 5),
                Pair.of(2, 2),
                null,
                "NESW"
            );
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("Error: patches must be specified (but can be empty)."));
        }
    }
}