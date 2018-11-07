package com.obprado.futurice;

import com.udojava.evalex.Expression;
import com.udojava.evalex.Expression.ExpressionException;

import java.math.BigDecimal;

public class Calculator {

    public BigDecimal calculate(String query) throws CalculusException {
        String simpleMathematicalExpression = "([0-9]|\\s|\\(|\\)|\\*|\\/|\\+|\\-|\\.)*";
        if (!query.matches(simpleMathematicalExpression)) {
            throw new CalculusException(query);
        }

        try {
            return new Expression(query).eval();
        } catch (ExpressionException | NumberFormatException e) {
            throw new CalculusException(query, e);
        }
    }
}
