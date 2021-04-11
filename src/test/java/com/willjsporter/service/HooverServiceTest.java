package com.willjsporter.service;

import com.willjsporter.model.HooverInput;
import com.willjsporter.model.HooverOutput;
import com.willjsporter.model.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            ""
        );

        assertThat(hooverService.run(testInput), is(new HooverOutput(Pair. of(1, 2), 1)));
    }

    @Test
    public void inputWithInstructionsShouldCountZeroPatchesIfNoneCleaned () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(4, 4),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "SWSS"
        );

        assertThat(hooverService.run(testInput), is(new HooverOutput(Pair. of(3, 1), 0)));
    }

    @Test
    public void whenInputAsksHooverToMoveOutOfBounds_hooverShouldRemainStatic () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(3, 1),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "EEEEENNNNSSSSSSSSSSSSWWWWWWWWWWENE"
        );

        assertThat(hooverService.run(testInput), is(new HooverOutput(Pair. of(2, 1), 0)));
    }

    @Test
    public void whenInstructionsMoveHooverOverDirtyPatches_hooverShouldMarkTheseAsCleaned () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(3, 1),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "NWWSWE"
        );

        assertThat(hooverService.run(testInput), is(new HooverOutput(Pair. of(1, 1), 2)));
    }

    @Test
    public void whenInstructionsMoveHooverOverSameDirtyPatch_hooverShouldOnlyTheseAsCleanedOnce () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(0, 2),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "EEWEWEE"
        );

        assertThat(hooverService.run(testInput), is(new HooverOutput(Pair. of(3, 2), 2)));
    }
}