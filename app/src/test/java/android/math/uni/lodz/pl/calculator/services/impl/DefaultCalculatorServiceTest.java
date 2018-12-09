package android.math.uni.lodz.pl.calculator.services.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for default implementation of {@link android.math.uni.lodz.pl.calculator.services.CalculatorService}.
 *
 * @author Piotr Krzyminski
 */
public class DefaultCalculatorServiceTest {

    private static final int VALUE_ONE = 2;
    private static final int VALUE_TWO = 2;
    private static final int RESULT_ADD = VALUE_ONE + VALUE_TWO;

    /**
     * Calculator service implementation.
     */
    private DefaultCalculatorService calculatorService;

    /**
     * Setup data for tests.
     */
    @Before
    public void setUp() {
        calculatorService = new DefaultCalculatorService();
    }

    /**
     * Tests addition of two integer values.
     */
    @Test
    public void testAddTwoInt() {
        int result = calculatorService.add(VALUE_ONE, VALUE_TWO);

        assertEquals(RESULT_ADD, result);
    }
}