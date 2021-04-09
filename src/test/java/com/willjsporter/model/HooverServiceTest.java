package com.willjsporter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class HooverServiceTest {

    HooverService hooverService;

    @BeforeEach
    public void setup() {
        hooverService = new HooverService();
    }

    @Test
    public void inputWithEmptyInstructionsShouldOnlyCleanSquareItIsDroppedOn () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(1, 2),
            List.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            ""
        );

        assertThat(hooverService.run(testInput), is(new HooverOutput(Pair. of(1, 2), 1)));
    }
}