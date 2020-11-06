package com.koutros.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class variables AKA fields
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculate;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews(){
        male = findViewById(R.id.radio_button_male);
        female = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);
        result = findViewById(R.id.textview_result);
    }

    private void setupButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculate();

                String ageText = age.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18){
                    displayResult(bmiResult);
                }else{
                    displayGuidance(bmiResult);
                }
                
            }
        });
    }


    private double calculate() {
       String feetText = feet.getText().toString();
       String inchesText = inches.getText().toString();
       String weightText = weight.getText().toString();

       //Converting the String input to int variables
       int feet = Integer.parseInt(feetText);
       int inches = Integer.parseInt(inchesText);
       int weight = Integer.parseInt(weightText);

       //Feet and Inches in Total Inches
       int totalInches = (feet * 12) + inches;

       //Convert inches to meters
       double heightInMeters = totalInches * 0.0254;

       //Calculate BMI
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        //Κρατάω μόνο τα 2 δεκαδικά απο το bmi
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight.";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight.";
        } else {
            //Display healthy
            fullResultString = bmiTextResult + " - You are healthy!";
        }
         result.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (male.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18 please ask your doctor for the healthy range for boys.";
        }else if(female.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18 please ask your doctor for the healthy range for girls.";
            //Display girl guidance
        }else{
            //Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18 please ask your doctor for the healthy range.";
        }
        result.setText(fullResultString);
    }
}