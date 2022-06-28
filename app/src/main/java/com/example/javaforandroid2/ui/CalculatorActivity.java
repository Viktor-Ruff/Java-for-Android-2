package com.example.javaforandroid2.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.javaforandroid2.R;
import com.example.javaforandroid2.model.Action;
import com.example.javaforandroid2.model.Calculator;
import com.example.javaforandroid2.model.CalculatorImpl;
import com.example.javaforandroid2.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView tvResult;
    private CalculatorPresenter presenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Double> digits = new HashMap<>();
        digits.put(R.id.btKey1, 1.0);
        digits.put(R.id.btKey2, 2.0);
        digits.put(R.id.btKey3, 3.0);
        digits.put(R.id.btKey4, 4.0);
        digits.put(R.id.btKey5, 5.0);
        digits.put(R.id.btKey6, 6.0);
        digits.put(R.id.btKey7, 7.0);
        digits.put(R.id.btKey8, 8.0);
        digits.put(R.id.btKey9, 9.0);
        digits.put(R.id.btKey0, 0.0);
        digits.put(R.id.btPi, 3.14159);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
                System.out.println(digits.get(view.getId()));
            }
        };

        findViewById(R.id.btKey1).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey2).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey3).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey4).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey5).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey6).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey7).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey8).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey9).setOnClickListener(digitClickListener);
        findViewById(R.id.btKey0).setOnClickListener(digitClickListener);
        findViewById(R.id.btPi).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.btPlus, Operator.SUM);
        operators.put(R.id.btMinus, Operator.SUB);
        operators.put(R.id.btMultiply, Operator.MULTIPLY);
        operators.put(R.id.btDivide, Operator.DIVIDE);
        operators.put(R.id.btRoot, Operator.ROOT);
        operators.put(R.id.btDegree, Operator.DEGREE);
        operators.put(R.id.btPercent, Operator.PERCENT);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
                System.out.println(operators.get(view.getId()));
            }
        };

        findViewById(R.id.btPlus).setOnClickListener(operatorClickListener);
        findViewById(R.id.btMinus).setOnClickListener(operatorClickListener);
        findViewById(R.id.btMultiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.btDivide).setOnClickListener(operatorClickListener);
        findViewById(R.id.btRoot).setOnClickListener(operatorClickListener);
        findViewById(R.id.btDegree).setOnClickListener(operatorClickListener);
        findViewById(R.id.btPercent).setOnClickListener(operatorClickListener);



        Map<Integer, Action> action = new HashMap<>();
        action.put(R.id.btDot, Action.DOT);
        action.put(R.id.btDelete, Action.DELETE);
        action.put(R.id.btClean, Action.CLEAN);
        action.put(R.id.btPlusMinus, Action.PLUSMINUS);
        action.put(R.id.btEquals, Action.EQUALS);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onActionPressed(action.get(view.getId()));
                System.out.println(action.get(view.getId()));
            }
        };

        findViewById(R.id.btDot).setOnClickListener(actionClickListener);
        findViewById(R.id.btDelete).setOnClickListener(actionClickListener);
        findViewById(R.id.btClean).setOnClickListener(actionClickListener);
        findViewById(R.id.btPlusMinus).setOnClickListener(actionClickListener);
        findViewById(R.id.btEquals).setOnClickListener(actionClickListener);
    }


    @Override
    public void showResult(String result) {
        tvResult.setText(result);

    }

}