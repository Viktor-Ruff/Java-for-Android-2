package com.example.javaforandroid2.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.javaforandroid2.R;
import com.example.javaforandroid2.model.CalculatorImpl;
import com.example.javaforandroid2.model.Operator;
import com.example.javaforandroid2.model.Theme;
import com.example.javaforandroid2.model.ThemeRepository;
import com.example.javaforandroid2.model.ThemeRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String KEY_ARG1 = "keyARG1";
    private TextView tvResult;
    private CalculatorPresenter presenter;
    private ThemeRepository themeRepository;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (savedInstanceState != null) {
            presenter.setArgOne(savedInstanceState.getDouble(KEY_ARG1));
        }*/

        themeRepository = ThemeRepositoryImpl.getInstance(this);

        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        if (getIntent().hasExtra("message")) {
            tvResult.setText(getIntent().getStringExtra("message"));
        }

        presenter = new CalculatorPresenter(this, new CalculatorImpl());


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }


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
        operators.put(R.id.btEquals, Operator.EQUALS);

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
        findViewById(R.id.btEquals).setOnClickListener(operatorClickListener);


        findViewById(R.id.btDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed();
            }
        });
        findViewById(R.id.btDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDeletePressed();
            }
        });
        findViewById(R.id.btClean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCleanPressed();
            }
        });
        findViewById(R.id.btPlusMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPlusMinusPressed();
            }
        });


        Button btWhiteTheme = findViewById(R.id.btWhiteTheme);
        Button btDarkTheme = findViewById(R.id.btDarkTheme);


        if (btWhiteTheme != null) {
            btWhiteTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    themeRepository.saveTheme(Theme.WHITE);

                    recreate();
                }
            });
        }

        if (btDarkTheme != null) {
            btDarkTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    themeRepository.saveTheme(Theme.DARK);

                    recreate();
                }
            });
        }

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();

                Theme selectedTheme = (Theme) intent.getSerializableExtra(SettingsActivity.EXTRA_THEME);

                themeRepository.saveTheme(selectedTheme);

                recreate();
            }

        });

        findViewById(R.id.btSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, SettingsActivity.class);
                intent.putExtra(SettingsActivity.EXTRA_THEME, themeRepository.getSavedTheme());

                themeLauncher.launch(intent);
            }
        });

    }

    /*@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putDouble(KEY_ARG1, presenter.getArgOne());
        super.onSaveInstanceState(outState);
    }*/

    @Override
    public void showResult(String result) {
        tvResult.setText(result);

    }

}