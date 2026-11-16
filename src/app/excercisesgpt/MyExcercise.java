package app.excercisesgpt;

import java.util.List;

public class MyExcercise {

    public NumberStats analyze(List<Integer> numbers) {

        Long sum = 0L;
        int min = numbers.getFirst().intValue();
        int max = numbers.getFirst().intValue();
        int evenCount = 0;
        for (Integer number : numbers) {
            sum = sum + number.longValue();
            if (max < number.intValue()) {
                max = number.intValue();
            }
            if (min > number.intValue()) {
                min = number.intValue();
            }
            if (number.intValue() % 2 == 0) {
                evenCount++;
            }
        }

        return new NumberStats(numbers.size(), sum, min, max, evenCount);
    }
}
