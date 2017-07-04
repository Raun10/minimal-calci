package com.android101.caclculatorv2;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

import static android.graphics.Color.MAGENTA;
import static android.graphics.PorterDuff.Mode.ADD;


public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private TextView bigScreen;
    HorizontalScrollView scrollView;
    ImageButton arrowScroll;
    private boolean decimalAdded = false; // Prevents you from writing numbers like 2.4..57.9
    private boolean equalToPressed = false; // To avoid updated result = old result + appending pressed operand
    int intResult;
    double result;

    // [was Spannable] strings declaration to change color of characters
    static final SpannableString ADD = new SpannableString("\u200A+\u200A");
    static final SpannableString SUBTRACT = new SpannableString("\u200A-\u200A");
    static final SpannableString MULTIPLY = new SpannableString("\u200A×\u200A");
    static final SpannableString DIVIDE = new SpannableString("\u200A/\u200A");
    static final SpannableString MOD = new SpannableString("%");
    static final SpannableString CAVET = new SpannableString("^");
    static final SpannableString SQUAREROOT = new SpannableString("√");
    static final SpannableString FACTORIAL = new SpannableString("!");

    // Operators strings declaration for quick & easy changes in future
    static final String ADDing = "+\u200A";
    static final String SUBTRACTing = "-\u200A";
    static final String MULTIPLYing = "×\u200A";
    static final String DIVIDing = "/\u200A";
    static final String MODulus = "%";
    static final String CAVETing = "^";
    static final String SQUAREROOTing = "√";
    static final String FACTORIALing = "!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_essentials);

        // To hide the actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // To make notification bar transparent
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Linking Textview to the java code
        screen = (TextView) findViewById(R.id.full_expression);
        bigScreen = (TextView) findViewById(R.id.big_screen);

        screen.setText("0");                    // Initialising the value to 0

        // Applying Custom Typeface = Light Roboto Font
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/roboto.light.ttf");
        screen.setTypeface(custom_font);
        bigScreen.setTypeface(custom_font);
        Button zero = (Button) findViewById(R.id.button0);
        zero.setTypeface(custom_font);
        Button one = (Button) findViewById(R.id.button1);
        one.setTypeface(custom_font);
        Button two = (Button) findViewById(R.id.button2);
        two.setTypeface(custom_font);
        Button three = (Button) findViewById(R.id.button3);
        three.setTypeface(custom_font);
        Button four = (Button) findViewById(R.id.button4);
        four.setTypeface(custom_font);
        Button five = (Button) findViewById(R.id.button5);
        five.setTypeface(custom_font);
        Button six = (Button) findViewById(R.id.button6);
        six.setTypeface(custom_font);
        Button seven = (Button) findViewById(R.id.button7);
        seven.setTypeface(custom_font);
        Button eight = (Button) findViewById(R.id.button8);
        eight.setTypeface(custom_font);
        Button nine = (Button) findViewById(R.id.button9);
        nine.setTypeface(custom_font);
        Button add = (Button) findViewById(R.id.button_add);
        add.setTypeface(custom_font);
        Button subtract = (Button) findViewById(R.id.button_subtract);
        subtract.setTypeface(custom_font);
        Button multiply = (Button) findViewById(R.id.button_multiply);
        multiply.setTypeface(custom_font);
        Button divide = (Button) findViewById(R.id.button_divide);
        divide.setTypeface(custom_font);
        Button decimal = (Button) findViewById(R.id.button_dot);
        decimal.setTypeface(custom_font);
        Button equal = (Button) findViewById(R.id.button_equal);
        equal.setTypeface(custom_font);
        Button cButton = (Button) findViewById(R.id.button_clear);
        cButton.setTypeface(custom_font);
        Button powerof = (Button) findViewById(R.id.button_cavet);
        powerof.setTypeface(custom_font);
        Button nthroot = (Button) findViewById(R.id.button_squareroot);
        nthroot.setTypeface(custom_font);
        Button percentage = (Button) findViewById(R.id.button_percentage);
        percentage.setTypeface(custom_font);
        Button openBracket = (Button) findViewById(R.id.button_open_bracket);
        openBracket.setTypeface(custom_font);
        Button closedBracket = (Button) findViewById(R.id.button_closed_bracket);
        closedBracket.setTypeface(custom_font);
        Button factorial = (Button) findViewById(R.id.button_factorial);
        factorial.setTypeface(custom_font);



//        theDecimalFormat.setMinimumIntegerDigits(1);
//        theDecimalFormat.setMaximumIntegerDigits(8);
//        theDecimalFormat.setMinimumFractionDigits(0);

        // For horizontal scrolling in case of long expression
        screen.setMovementMethod(new ScrollingMovementMethod());

        // To show scrolling arrow when app is launched
        scrollView = (HorizontalScrollView) findViewById(R.id.operator_row);
        arrowScroll = (ImageButton)findViewById(R.id.scrollButton);
        arrowScroll.setVisibility(View.VISIBLE);

        arrowScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        scrollView.smoothScrollTo(1000, 0);
                        arrowScroll.setVisibility(View.INVISIBLE); // to make the arrow invisible once the user knows of scrolling operators
                        //arrowScroll.setBackgroundResource(R.drawable.double_arrow_right);
            }
        });

        // To clear on long pressing backspace
        findViewById(R.id.button_clear).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                screen.setText("0");
                decimalAdded = false;
                bigScreen.setText("");
                result = 0;
                //operandOne = 0;
                return true;
            }
        });

    }



    public void onClick(View v) {

        if (v instanceof Button) {

            Button clickedButton = (Button) v;
            String screenString = screen.getText().toString();
            String lastChar;
            String secondLastChar = "";

            // Fetching and storing the last character
            lastChar = screenString.substring(screenString.length() - 1);

            if (screenString.length() > 1){
                secondLastChar = screenString.substring(screenString.length() - 2);
            }
            switch (v.getId()) {

                case R.id.button_clear:
                    if (screenString.length() > 1 ) {
                        if(screenString.endsWith(String.valueOf(ADD)) || screenString.endsWith(String.valueOf(SUBTRACT)) || screenString.endsWith(String.valueOf(MULTIPLY)) || screenString.endsWith(String.valueOf(DIVIDE)))
                        {
                            screen.setText(screenString.substring(0, screen.getText().toString().length() - 3));
                        }
                        else
                        {
                            screen.setText(screenString.substring(0, screen.getText().toString().length() - 1));
                        }
                        printToBigScreen();
                    }
                    else if(screenString.length() == 1)
                    {
                        screen.setText("0");

                        decimalAdded = false;
                        bigScreen.setText("");
                        result = 0;
                    }
                    else
                    {
                        screen.setText("0");
                    }
                    break;

                case R.id.button_dot:
                    if (!decimalAdded) {
                        // To add "0." after "+", "-", "*", "√", "/", "%", "^", "("
                        if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing) || lastChar.equals(SQUAREROOTing)
                                || secondLastChar.equals(DIVIDing) || lastChar.equals(MODulus) || lastChar.equals(CAVETing) || lastChar.equals("(") || lastChar.equals("-")) {
                            screen.append("0.");
                        }
                        else if(lastChar.equals(FACTORIALing)){
                            // To not add decimal after "!"
                            screen.append("");
                        }
                        else{
                            screen.append(".");
                        }

                        decimalAdded = true;
                    }
                    break;

                case R.id.button_add:
                    if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(".") || secondLastChar.equals("(-")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing) || lastChar.equals("(")) {
                        screen.append("");
                    }
                    else
                    {
                        //ADD.setSpan(new ForegroundColorSpan(getColor(R.color.purple)), 0, 3, 0);
                        screen.append(ADD);
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_subtract:
                    // Change operator color
                    if ( lastChar.equals(".") || lastChar.equals(MODulus) || secondLastChar.equals("(-")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing) ) {
                        screen.append("");
                    }
                    else if(secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(CAVETing)){
                        screen.append("(-");
                    }
                    else if(lastChar.equals("(")){
                        screen.append("-");
                    }
                    else
                    {
                        screen.append(SUBTRACT);
                        //setOperandOne();
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_multiply:
                    if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(".") || lastChar.equals(MODulus) || secondLastChar.equals("(-")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing) || lastChar.equals("("))
                    {
                        screen.append("");
                    }
                    else
                    {
                        screen.append(MULTIPLY);
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_divide:
                    if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(".") || lastChar.equals(MODulus) || secondLastChar.equals("(-")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing) || lastChar.equals("(")) {
                        screen.append("");
                    }
                    else
                    {
                        screen.append(DIVIDE);
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_percentage:
                    if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(".") || lastChar.equals(MODulus) || secondLastChar.equals("(-")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing) || lastChar.equals("(")) {
                        screen.append("");
                    }
                    else
                    {
                        screen.append(MOD);
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_cavet:
                    if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(".") || lastChar.equals(MODulus) || secondLastChar.equals("(-")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing) || lastChar.equals("("))
                    {
                        screen.append("");
                    }
                    else
                    {
                        screen.append(CAVET);
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_squareroot:
                    if ( lastChar.equals(".") || lastChar.equals(SQUAREROOTing) || lastChar.equals("(") || secondLastChar.equals("(-"))
                    {
                        screen.append("");
                    }
                    // If screenString is 0 then input √
                    else if(screenString.equals("0"))
                    {
                        screen.setText(SQUAREROOT);
                    }
                    else if(secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(MODulus) || lastChar.equals(CAVETing))
                    {
                        screen.append(SQUAREROOT);
                        //setOperandOne();
                    }
                    // if √ pressed after number then input *√
                    else
                    {
                        screen.append(MULTIPLY);
                        screen.append("√");
                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button0:
                    // don't allow 0 after operators
                    if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(MODulus) || screenString.equals("0")
                            || lastChar.equals(CAVETing) || lastChar.equals(SQUAREROOTing))
                    {
                        screen.append("");
                    }
                    else
                    {
                        screen.append(clickedButton.getText().toString());
                        printToBigScreen();

                    }
                    decimalAdded = false;
                    equalToPressed = false;
                    break;

                case R.id.button_open_bracket:
                    if(secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing)
                            || secondLastChar.equals(DIVIDing) || lastChar.equals(MODulus) || lastChar.equals(CAVETing)
                            || lastChar.equals(SQUAREROOTing) || lastChar.equals("(") )
                    {
                        screen.append("(");
                    }
                    // when screenString == 0 then set screen to '('
                    else if(screenString.equals("0"))
                    {
                        screen.setText("(");
                    }
                    else
                    {
                        screen.append(MULTIPLY);
                        screen.append("(");
                    }
                    equalToPressed = false;
                    break;

                case R.id.button_closed_bracket:
                    int countOpenBracket = 0;
                    int countClosedBracket = 0;
                    for(int i=0; i<screen.getText().toString().length(); i++)
                    {
                        if(screen.getText().toString().charAt(i) == '(')
                        {
                            countOpenBracket++;
                        }
                        if(screen.getText().toString().charAt(i) == ')')
                        {
                            countClosedBracket++;
                        }
                    }
                    // To avoid putting more number of ')' than number of '('
                    if(screenString.contains("("))
                    {
                        if (countOpenBracket > countClosedBracket)
                        {
                            if (secondLastChar.equals(ADDing) || secondLastChar.equals(SUBTRACTing) || secondLastChar.equals(MULTIPLYing) || lastChar.equals(SQUAREROOTing) || lastChar.equals(".")
                                    || secondLastChar.equals(DIVIDing) || lastChar.equals(MODulus) || lastChar.equals(CAVETing) || lastChar.equals("(") || lastChar.equals("-"))
                            {
                                screen.append("");
                            }
                            else
                            {
                                screen.append(")");
                            }
                        }
                        else
                        {
                            screen.append("");
                        }
                    }
                    printToBigScreen();
                    equalToPressed = false;
                    break;

                case R.id.button_equal:
                    equalToPressed = true;
                    if(result != 0)
                    {
                        // result = rational number and less than 2147483647
                        if(result % 1 == 0 && result < 2147483647)
                        {  //1 divides to number perfectly, there is no decimal
                            intResult = (int)result;
                            if(String.valueOf(intResult).length() > 13)
                            {
                                DecimalFormat precision = new DecimalFormat("#.##########");
                                screen.setText(precision.format(intResult));
                            }
                            else
                            {
                                screen.setText(String.valueOf(intResult));
                            }
                        }
                        // result = rational number but greater than 2147483647 (max Int value)
                        else if(result % 1 == 0)
                        {
                            DecimalFormat maxInt = new DecimalFormat("0.###########E0");
                            screen.setText(maxInt.format(result));
                        }
                        // when result = not rational number; a decimal number
                        else
                        {
                            //int length = screen.getText().toString().length();
                            if(String.valueOf(result).length() > 8)
                            {
                                NumberFormat formatter = new DecimalFormat("0.###########E0");
                                screen.setText(formatter.format(result));
                            }
                            // if user entered decimal places then give unformatted result
                            if(screen.getText().toString().contains("."))
                            {
                                screen.setText(String.valueOf(result));
                            }
                            else
                            // limit number of decimal places to 10 max in screen
                            {
                                DecimalFormat precision = new DecimalFormat("0.##########");
                                screen.setText(precision.format(result));
                            }
                        }
                    }
                    else
                    {
                        screen.setText("0");
                    }

                    break;

                case R.id.button_factorial:
                    if (lastChar.equals(ADDing) || lastChar.equals(SUBTRACTing) || lastChar.equals(MULTIPLYing) || lastChar.equals(SQUAREROOTing) ||
                            lastChar.equals(DIVIDing) || lastChar.equals(MODulus) || lastChar.equals(CAVETing) || lastChar.equals("(") || lastChar.equals(FACTORIALing))
                    {
                            screen.append("");
                    }
                    else
                    {
                        FACTORIAL.setSpan(new ForegroundColorSpan(getColor(R.color.purple)), 0, 1, 0);
                        screen.append(FACTORIAL);
                        printToBigScreen();
                    }
                    equalToPressed = false;
                    break;

//                case R.id.scrollButton:
//                    scrollView.scrollTo(240, 0);
//                    break;

                default:
                    if (equalToPressed){    // To set the result to pressed operand after equalTo is clicked
                        equalToPressed = false;
                        decimalAdded = false;
                        bigScreen.setText(clickedButton.getText().toString());
                        screen.setText(clickedButton.getText().toString());
                        result = Double.parseDouble(clickedButton.getText().toString());
                    }
                    else {
                        if (screenString.equals("0")) {
                            // to remove the initial 0 and show the typed operand
                            screen.setText(clickedButton.getText().toString());
                        } else {
                            screen.append(clickedButton.getText().toString());
                        }
                        printToBigScreen();
                    }
                    break;
            }
        }
    }

    public void printToBigScreen(){

        CalculatorBrain compute = new CalculatorBrain();
        compute.setScreen(screen.getText().toString());
        result = compute.computeOperation();
        //computeOperation();
        if(result != 0)
        {
            // result = rational number and less than max Int value
            if(result % 1 == 0 && result < 2147483647)
            {  //1 divides into number perfectly, there is no decimal
                intResult = (int)result;
                bigScreen.setText(String.valueOf(intResult));
            }
            // result = rational number but greater than max Int value
            else if(result % 1 == 0){
                DecimalFormat maxInt = new DecimalFormat("0.#####E0");
                bigScreen.setText(maxInt.format(result));
            }
            else // when result = not rational number; a decimal number
            {
                //int length = screen.getText().toString().length();
                if(String.valueOf(result).length() > 8)
                {
                    NumberFormat formatter = new DecimalFormat("0.#####E0");
                    bigScreen.setText(formatter.format(result));
                }
                else
                {
                    bigScreen.setText(String.valueOf(result));
                }
                // if user entered decimal places then give unformatted result

                if(screen.getText().toString().contains("."))
                {
                    DecimalFormat precision = new DecimalFormat("0.#####");
                    bigScreen.setText(precision.format(result));
                }
                else
                // limit number of decimal places to 5 max in bigScreen
                {
                    DecimalFormat precision = new DecimalFormat("0.#####");
                    bigScreen.setText(precision.format(result));
                }

            }
        }
        else
        {
                bigScreen.setText("0");
        }
        // To avoid showing NaN
        if (bigScreen.getText().toString().equals("NaN"))
        {
            bigScreen.setText("");
        }
    }
}
