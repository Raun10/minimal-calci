package com.android101.caclculatorv2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;
import java.util.regex.Pattern;

/**
 * Created by raunaqshah on 13/04/17 for keeping the code for calculation separate
 */

class CalculatorBrain {

    private String mScreen;

    void setScreen(String mScreen) {
        this.mScreen = mScreen;
    }

    CalculatorBrain(){
    }

    double computeOperation() {
        double mResult;
        String expression = mScreen;
        String[] operators = {"×", "√"};
        final String multiplySign = Pattern.quote(operators[0]);
        final String squareRootSign = Pattern.quote(operators[1]);
        expression = expression.replaceAll("\u200A", "");
        expression = expression.replaceAll(multiplySign, "*");
        expression = expression.replaceAll("(\\d+\\!)", "($1)");
        //expression = expression.replaceAll("\\√+(\\d)", "($1)");
        expression = expression.replaceAll(squareRootSign, "sqrt");
        expression = expression.replaceAll("sqrt(\\d+)", "sqrt($1)");
        Expression e = new Expression(expression);
        Log.i("Expression: ", expression );
        mResult = e.calculate();
        return mResult;
    }
}
