package com.example.javaforandroid2.ui;

import android.icu.number.NumberFormatter;
import android.icu.text.DecimalFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.javaforandroid2.model.Action;
import com.example.javaforandroid2.model.Calculator;
import com.example.javaforandroid2.model.Operator;

/**
 * Created by Viktor-Ruff
 * Date: 26.06.2022
 * Time: 12:08
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class CalculatorPresenter {

    private final CalculatorView view;
    private final Calculator calculator;
    private final DecimalFormat formatter = new DecimalFormat("#.#######");


    private double argOne;
    private Double argTwo;
    private double lastCount = 0;

    private Operator selectedOperator;

    private int countSymbolAfterDot = 0;

    private boolean dotPressed = false;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(Double digit) {

        if (argTwo == null) {

            if (dotPressed) {

                countSymbolAfterDot++;
                argOne = argOne + (digit / Math.pow(10, countSymbolAfterDot));

            } else {

                argOne = argOne * 10 + digit;
            }

            showFormatted(argOne);

        } else {

            if (dotPressed) {

                countSymbolAfterDot++;
                argTwo = argTwo + (digit / Math.pow(10, countSymbolAfterDot));

            } else {

                argTwo = argTwo * 10 + digit;
            }

            showFormatted(argTwo);
        }

    }

    public void onOperatorPressed(Operator operator) {

        if (selectedOperator != null) {

            argOne = calculator.execute(argOne, argTwo, selectedOperator);
            showFormatted(argOne);

        }

        argTwo = 0.0;
        selectedOperator = operator;

        dotPressed = false;
        countSymbolAfterDot = 0;

    }


    private void showFormatted(double value) {

        view.showResult(formatter.format(value));
    }

    public void onDotPressed() {

        dotPressed = true;

    }

    public void onDeletePressed() {

        if (selectedOperator == null || selectedOperator == Operator.EQUALS) {

            lastCount = argOne % 10;
            argOne = (argOne - lastCount) / 10;
            showFormatted(argOne);

        } else {

            lastCount = argTwo % 10;
            argTwo = (argTwo - lastCount) / 10;
            showFormatted(argTwo);

        }

    }

    public void onCleanPressed() {

        argOne = 0.0;
        argTwo = null;
        selectedOperator = null;
        showFormatted(0);
        dotPressed = false;
        countSymbolAfterDot = 0;
    }

    public void onPlusMinusPressed() {

        if (selectedOperator == null || selectedOperator == Operator.EQUALS) {

            argOne = -argOne;
            showFormatted(argOne);

        } else {

            argTwo = -argTwo;
            showFormatted(argTwo);

        }
    }

    public double getArgOne() {
        return argOne;
    }

    public void setArgOne(double argOne) {
        this.argOne = argOne;
    }
}
