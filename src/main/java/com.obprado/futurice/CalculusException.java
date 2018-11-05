package com.obprado.futurice;

public class CalculusException extends Exception {

    public CalculusException(String expression, Throwable cause) {
        super(String.format("Invalid input '%s'. Expressions must be composed exclusively of " +
                "numbers, whitespaces, round brackets and the operators: *, /, +, -", expression), cause);
    }
}
