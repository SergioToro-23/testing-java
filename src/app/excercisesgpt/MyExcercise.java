package app.excercisesgpt;

import java.util.List;

public class MyExcercise {

    public NumberStats analyze(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException(
                    "numbers cannot be empty");
        }

        long sum = 0L;
        int min = numbers.getFirst().intValue();
        int max = numbers.get(0).intValue();
        int evenCount = 0;
        for (Integer number : numbers) {
            if (number == null) {
                throw new IllegalArgumentException(
                        "Numbers cannot contain null elements");
            }
            int value = number;
            sum += number.longValue();
            if (max < value) {
                max = value;
            }
            if (min > value) {
                min = value;
            }
            if (value % 2 == 0) {
                evenCount++;
            }
        }

        return new NumberStats(
                numbers.size(),
                sum,
                min,
                max,
                evenCount);
    }
}
