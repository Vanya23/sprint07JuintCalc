package calc;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    ReversePolishNotationCalculator pcalc = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition2numbers() {
        String input = "4 3 +";
        assertEquals(7, pcalc.calculatePolishNotation(input));

    }
    @Test
    public void shouldCalculateAddition3numbers() {
        String input = "4 3 5 + +";
        assertEquals(12, pcalc.calculatePolishNotation(input));

    }
    @Test
    public void shouldCalculateMinus2numbers() {
        String input = "4 3 -";
        assertEquals(1, pcalc.calculatePolishNotation(input));

    }
    @Test
    public void shouldCalculateMinus3numbers() {
        String input = "4 3 5 - -";
        assertEquals(6, pcalc.calculatePolishNotation(input));

    }

    @Test
    public void shouldCalculateMult2numbers() {
        String input = "12    15 *";
        assertEquals(180, pcalc.calculatePolishNotation(input));

    }

    @Test
    public void shouldCalculateMult3numbers() {
        String input = "1 1000 5 * *";
        assertEquals(5000, pcalc.calculatePolishNotation(input));

    }

    @Test
    public void shouldCalculateMixed5numbers() {
        String input = "500 7 12 58 98 78 1 0 + - * - * + -";
        assertEquals(90349, pcalc.calculatePolishNotation(input));

    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}