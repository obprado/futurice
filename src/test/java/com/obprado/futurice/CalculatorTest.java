package com.obprado.futurice;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CalculatorTest {

    @Test
    public void shouldEvaluateComplexExpression() throws CalculusException {
        String expression = "2 * (5/(3+3)) - 23 * (2      -3)";
        BigDecimal result = new Calculator().calculate(expression);
        assertThat(result.toString()).isEqualTo("24.66667");
    }

    @Test
    public void shouldRejectExpressionsWithInvalidCharacters() {
        String expression = "jibberish";
        assertThatThrownBy(() -> new Calculator().calculate(expression))
                .hasMessage("Invalid input 'jibberish'. Expressions must be composed exclusively of" +
                        "numbers, whitespaces, round brackets and the operators: *, /, +, -");
    }

}