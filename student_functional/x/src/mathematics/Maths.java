package mathematics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Maths {

    public boolean checkNumber (int number) {
       return IntStream.range(1,number)
                .filter(n -> number % n == 0)
                .sum() == number;

    }

    public List<Integer> findSquareNumbers(int n){
        return IntStream.rangeClosed(1,n)
                .mapToObj(v -> v * v)
                .collect(Collectors.toList());
    }

    public boolean findPrimeNumbers(int number) {
        return IntStream.range(2,number)
                .noneMatch(v-> number % v == 0);

    }
}
