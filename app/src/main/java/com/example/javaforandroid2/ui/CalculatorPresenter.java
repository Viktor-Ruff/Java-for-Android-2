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

    private CalculatorView view;
    private Calculator calculator;
    private DecimalFormat formatter = new DecimalFormat("#.#####");
    private double argOne;
    private Double argTwo;
    private double result;
    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(Double digit) {

        if (argTwo == null) {

            argOne = argOne * 10 + digit;

            showFormatted(argOne);

        } else {

            argTwo = argTwo * 10 + digit;

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
    }

    public void onActionPressed(Action action) {

        switch (action) {

            case EQUALS:


            case CLEAN:

                argOne = 0.0;
                argTwo = 0.0;
                showFormatted(argOne);

            case PLUSMINUS:

                argOne = -argOne;
                showFormatted(argOne);

            case DOT:


            case DELETE:
        }


    }

    private void showFormatted(double value) {

        view.showResult(formatter.format(value));
    }
}
