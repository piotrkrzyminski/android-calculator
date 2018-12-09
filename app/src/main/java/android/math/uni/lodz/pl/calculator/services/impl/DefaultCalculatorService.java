package android.math.uni.lodz.pl.calculator.services.impl;

import android.math.uni.lodz.pl.calculator.services.CalculatorService;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation of {@link CalculatorService}.
 *
 * @author Piotr Kzryminski.
 */
public class DefaultCalculatorService implements CalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCalculatorService.class);

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public double parseExpression(String expression) throws IllegalArgumentException {
        Expression exp = new ExpressionBuilder(expression).build();
        return exp.evaluate();
    }
}
