package android.math.uni.lodz.pl.calculator.services;

/**
 * Calculator interface. Defines types of available calculations.
 *
 * @author Piotr Krzyminski.
 */
public interface CalculatorService {

    /**
     * Performs addition of two integers.
     *
     * @param a first integer value.
     * @param b second integer value.
     * @return result of addition two integer numbers.
     */
    int add(int a, int b);
}
