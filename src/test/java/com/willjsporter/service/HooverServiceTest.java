package com.willjsporter.service;

import com.willjsporter.model.HooverInput;
import com.willjsporter.model.HooverOutput;
import com.willjsporter.model.Pair;
import com.willjsporter.repository.HooverInputRepository;
import com.willjsporter.repository.HooverOutputRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HooverServiceTest {

    private HooverService hooverService;
    @Mock
    private HooverInputRepository hooverInputRepository;
    @Mock
    private HooverOutputRepository hooverOutputRepository;

    @BeforeEach
    public void setup() {
        hooverService = new HooverService(hooverInputRepository, hooverOutputRepository);
    }

    @Test
    public void inputWithEmptyInstructionsShouldOnlyCleanSquareItIsDroppedOn () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(1, 2),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            ""
        );

        HooverOutput expectedOutput = new HooverOutput(Pair. of(1, 2), 1);
        when(hooverInputRepository.save(testInput)).thenReturn(testInput);
        when(hooverOutputRepository.save(expectedOutput)).thenReturn(expectedOutput);

        assertThat(hooverService.run(testInput), is(expectedOutput));
    }

    @Test
    public void inputWithInstructionsShouldCountZeroPatchesIfNoneCleaned () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(4, 4),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "SWSS"
        );
        HooverOutput expectedOutput = new HooverOutput(Pair. of(3, 1), 0);
        when(hooverInputRepository.save(testInput)).thenReturn(testInput);
        when(hooverOutputRepository.save(expectedOutput)).thenReturn(expectedOutput);

        assertThat(hooverService.run(testInput), is(expectedOutput));
    }

    @Test
    public void whenInputAsksHooverToMoveOutOfBounds_hooverShouldRemainStatic () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(3, 1),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "EEEEENNNNSSSSSSSSSSSSWWWWWWWWWWENE"
        );

        HooverOutput expectedOutput = new HooverOutput(Pair. of(2, 1), 0);
        when(hooverInputRepository.save(testInput)).thenReturn(testInput);
        when(hooverOutputRepository.save(expectedOutput)).thenReturn(expectedOutput);

        assertThat(hooverService.run(testInput), is(expectedOutput));
    }

    @Test
    public void whenInstructionsMoveHooverOverDirtyPatches_hooverShouldMarkTheseAsCleaned () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(3, 1),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "NWWSWE"
        );

        HooverOutput expectedOutput = new HooverOutput(Pair. of(1, 1), 2);
        when(hooverInputRepository.save(testInput)).thenReturn(testInput);
        when(hooverOutputRepository.save(expectedOutput)).thenReturn(expectedOutput);

        assertThat(hooverService.run(testInput), is(expectedOutput));
    }

    @Test
    public void whenInstructionsMoveHooverOverSameDirtyPatch_hooverShouldOnlyTheseAsCleanedOnce () {
        final HooverInput testInput = new HooverInput(
            Pair.of(5, 5),
            Pair.of(0, 2),
            Set.of(Pair.of(1, 2), Pair.of(2, 2), Pair.of(2, 3)),
            "EEWEWEE"
        );

        HooverOutput expectedOutput = new HooverOutput(Pair. of(3, 2), 2);
        when(hooverInputRepository.save(testInput)).thenReturn(testInput);
        when(hooverOutputRepository.save(expectedOutput)).thenReturn(expectedOutput);

        assertThat(hooverService.run(testInput), is(expectedOutput));
    }
}