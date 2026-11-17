package app.excercisesgptbasics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyExcerciseTest {

    private List<Integer> numberList;

    private MyExcercise myExcercise;

    @BeforeEach
    void setUp() {
        myExcercise = new MyExcercise();
        numberList = List.of(1, 2, 3, 4, 5, 6);
    }

    @Test
    void shouldCalculateCount() {
        NumberStats numberStats = myExcercise.analyze(numberList);
        assertEquals(6, numberStats.count());
    }

    @Test
    void shouldCalculateSum() {
        NumberStats numberStats = myExcercise.analyze(numberList);
        assertEquals(21, numberStats.sum());
    }

    @Test
    void shouldCalculateMin() {
        NumberStats numberStats = myExcercise.analyze(numberList);
        assertEquals(1, numberStats.min());
    }

    @Test
    void shouldCalculateMax() {
        NumberStats numberStats = myExcercise.analyze(numberList);
        assertEquals(6, numberStats.max());
    }

    @Test
    void shouldCalculateEvenCount() {
        NumberStats numberStats = myExcercise.analyze(numberList);
        assertEquals(3, numberStats.evenCount());
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> myExcercise.analyze(List.of()));
    }

    @Test
    void shouldCalculateStatsWithNegativeNumbers() {
        var result = myExcercise.analyze(
                List.of(-5, -2, -10, 4));

        assertEquals(4, result.count());
        assertEquals(-13, result.sum());
        assertEquals(-10, result.min());
        assertEquals(4, result.max());
        assertEquals(3, result.evenCount());
    }
}
