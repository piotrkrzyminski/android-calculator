package android.math.uni.lodz.pl.calculator.services.impl;

import android.math.uni.lodz.pl.calculator.services.CalculatorService;

/**
 * Default implementation of {@link CalculatorService}.
 *
 * @author Piotr Kzryminski.
 */
public class DefaultCalculatorService implements CalculatorService {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
