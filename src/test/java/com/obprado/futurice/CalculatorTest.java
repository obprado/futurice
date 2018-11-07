package com.obprado.futurice;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CalculatorTest {

    @Test
    public void shouldEvaluateComplexExpression() throws CalculusException {
        shouldCalculate("2 * (5/(3+3)) - 23 * (2      -3)", "24.66667");
    }

    @Test
    public void shouldEvaluateDecimalNumbers() throws CalculusException {
        shouldCalculate("1.5 * 3", "4.5");
        shouldCalculate("0.0 + 1.0", "1");
        throwsCalculusExceptionFor("1........5");
    }

    @Test
    public void shouldPrioritizeMultiplyAndDivideOverSubstractAndAdd() throws CalculusException {
        shouldCalculate("40 - 40 * 2 + 1", "-39");
        shouldCalculate("40 - 40 / 2 + 1", "21");
    }

    @Test
    public void shouldOverrideOperationsPriorityAccordingToParenthesis() throws CalculusException {
        shouldCalculate("(40 - 40) * 2 + 1", "1");
        shouldCalculate("(40 - 40) * (2 + 1)", "0");
        shouldCalculate("(40 - 40) / 2 + 1", "1");
        shouldCalculate("(40 - 40) / (2 + 1)", "0");
    }

    @Test
    public void shouldIgnoreWhiteSpacesLineBreaksAndTabs() throws CalculusException {
        shouldCalculate("20 - 7", "13");
        shouldCalculate("20-7", "13");
        shouldCalculate("20 \n - \n 7", "13");
        shouldCalculate("20 \t - \t 7", "13");
        shouldCalculate("                20-7", "13");
        shouldCalculate("20-7              ", "13");
        shouldCalculate("           20             -               7              ", "13");
    }

    @Test
    public void shouldRejectExpressionsWithUnparseableExpressions() {
        throwsCalculusExceptionFor("jibberish");
    }


    @Test
    public void shouldRejectExpressionsWithForbiddenMathematicalOperators() {
        // Only *, /, +, - and () allowed, otherwise it's hard to guarantee protection against code injection!
        throwsCalculusExceptionFor("sin(3)");
        throwsCalculusExceptionFor("3^2");
        throwsCalculusExceptionFor("3%2");
        throwsCalculusExceptionFor("log(2)");
        throwsCalculusExceptionFor("5!");
        throwsCalculusExceptionFor("2 + 3 = 5");
    }

    @Test
    public void shouldRejectExpressionsWithInvalidCompositionOfValidCharacters() {
        throwsCalculusExceptionFor("3 ) (");
        throwsCalculusExceptionFor("3 ----");
        throwsCalculusExceptionFor("2 */*/*/ 12");
        throwsCalculusExceptionFor("2 4321432 412 4123 4312 4132");
        throwsCalculusExceptionFor("                     ");
        throwsCalculusExceptionFor("*");
        throwsCalculusExceptionFor("-");
        throwsCalculusExceptionFor("+");
        throwsCalculusExceptionFor("/");
        throwsCalculusExceptionFor("(");
        throwsCalculusExceptionFor(")");
        throwsCalculusExceptionFor("()");
        throwsCalculusExceptionFor("(-)");
        throwsCalculusExceptionFor("(3)+");
        throwsCalculusExceptionFor("*(3)");
        throwsCalculusExceptionFor("/(3)");
        throwsCalculusExceptionFor("...................................");
    }

    private void shouldCalculate(String expression, String expected) throws CalculusException {
        BigDecimal result = new Calculator().calculate(expression);
        assertThat(result.toString()).isEqualTo(expected);
    }

    private void throwsCalculusExceptionFor(String expression) {
        assertThatThrownBy(() -> new Calculator().calculate(expression))
                .hasMessage("Invalid input '" + expression + "'. Expressions must be composed exclusively of " +
                        "numbers, whitespaces, round brackets and the operators: *, /, +, -");
    }

}