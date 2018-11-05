package com.obprado.futurice;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

public class Calculator {

    public BigDecimal calculate(String query) {
        return new Expression(query).eval();
    }
}
