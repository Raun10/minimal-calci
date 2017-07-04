package com.android101.calculatorv20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private boolean decimalAdded = false; // Prevents you from writing numbers like 2.4..57.9


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.full_expression);
        screen.setText("0"); // Initialising the value to 0
    }

    public void onClick (View v){

        if (v instanceof Button) {

            Button clickedButton = (Button) v;
            String screenString = screen.getText().toString();
            String lastChar = screenString.substring(screenString.length() - 1); // Fetching and storing the last character

            switch (v.getId()) {
                case R.id.button_clear:
                    screen.setText("0");
                    decimalAdded = false;
                    break;

                case R.id.button_dot:
                    if (!decimalAdded) {
                        if (lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("×") || lastChar.equals("÷")) {
                            screen.append("0"); // Add a zero before adding the decimal point
                        }
                        screen.append(".");
                        decimalAdded = true;
                    }
                    break;

                case R.id.button_add:
                    if(lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("×") || lastChar.equals("÷") || lastChar.equals(".")){
                        screen.append("");
                    }
                    else {
                        screen.append("+");
                    }
                    decimalAdded = false;
                    break;
                case R.id.button_subtract:
                    if(lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("×") || lastChar.equals("÷") || lastChar.equals(".")){
                        screen.append("");
                    }
                    else {
                        screen.append("-");
                    }
                    decimalAdded = false;
                    break;
                case R.id.button_multiply:
                    if(lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("×") || lastChar.equals("÷") || lastChar.equals(".")){
                        screen.append("");
                    }
                    else {
                        screen.append("×");
                    }
                    decimalAdded = false;
                    break;
                case R.id.button_divide:
// If the screen text is 0, you should still permit the calculation, so I've removed that condition
                    if(lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("×") || lastChar.equals("÷") || lastChar.equals(".")){
                        screen.append("");
                    }
                    else {
                        screen.append("÷");
                    }
                    decimalAdded = false;

                    /*if (lastChar.equals(".")) {
                        screen.append("0"); // Append a 0 after the decimal point
                    }
                    decimalAdded = false;
                    screen.append(clickedButton.getText().toString());*/
                    break;

/* case R.id.button_equals:
computeString();
decimalAdded = false;
break;
*/

                default:
                    if (screenString.equals("0")) {
                        screen.setText(clickedButton.getText().toString());
                    } else {
                        screen.append(clickedButton.getText().toString());
                    }
            }
        }
    }
}