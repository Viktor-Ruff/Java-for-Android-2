package com.example.javaforandroid2.model;

/**
 * Created by Viktor-Ruff
 * Date: 26.06.2022
 * Time: 11:38
 */

public class CalculatorImpl implements Calculator {


    @Override
    public double execute(double arg1, double arg2, Operator operator) {

        switch (operator) {
            case SUM:
                return arg1 + arg2;

            case SUB:
                return arg1 - arg2;

            case MULTIPLY:
                return arg1 * arg2;

            case DIVIDE:
                return arg1 / arg2;

            case PERCENT:
                return arg1 * (arg2 * 0.01);

            case ROOT:
                return Math.sqrt(arg1);

            case DEGREE:
                return arg1 * arg1;

            case EQUALS:
                return arg1;

        }

        return 0.0;
    }



}
