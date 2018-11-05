package com.obprado.futurice;

import com.udojava.evalex.Expression;
import com.udojava.evalex.Expression.ExpressionException;

import java.math.BigDecimal;

public class Calculator {

    public BigDecimal calculate(String query) throws CalculusException {
        try {
            return new Expression(query).eval();
        } catch (ExpressionException e) {
            throw new CalculusException(query, e);
        }
    }
}
