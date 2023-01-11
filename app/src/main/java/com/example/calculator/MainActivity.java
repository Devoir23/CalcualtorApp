package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView value_tv, answer_tv;
    MaterialButton one,two,three,four,five,six,seven,eight,
            nine,zero,bracketopen,bracketclose,divide,multiply,addition,subtraction,clear,delete,dot,answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value_tv = findViewById(R.id.value);
        answer_tv = findViewById(R.id.result);

        assignId(one,R.id.one);
        assignId(two,R.id.Two);
        assignId(three,R.id.Three);
        assignId(four,R.id.Four);
        assignId(five,R.id.Five);
        assignId(six,R.id.Six);
        assignId(seven,R.id.Seven);
        assignId(eight,R.id.Eight);
        assignId(nine,R.id.Nine);
        assignId(zero,R.id.Zero);
        assignId(dot,R.id.point);
        assignId(multiply,R.id.mul_btn);
        assignId(divide,R.id.div_btn);
        assignId(addition,R.id.add_btn);
        assignId(subtraction,R.id.Sub_btn);
        assignId(clear,R.id.clear_btn);
        assignId(delete,R.id.All_Clear);
        assignId(answer,R.id.equl_btn);
        assignId(bracketopen,R.id.openBract);
        assignId(bracketclose,R.id.closeBract);



    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    public void onClick(View v){
        MaterialButton button = (MaterialButton)v;
        String btnText = button.getText().toString(); //get text wrt button clicked
        String data_cal = value_tv.getText().toString(); //store value to string
        if(btnText.equals("AC")){
            value_tv.setText(" ");
            answer_tv.setText("0");
            return;
        }

        if(btnText.equals("=")){
            value_tv.setText(answer_tv.getText());
            return;
        }

        if(btnText.equals("C")){
            data_cal = data_cal.substring(0, data_cal.length()-1);
        }else{
            data_cal = data_cal + btnText;
        }

        value_tv.setText(data_cal);
        String finalresult = result(data_cal);

        if(!finalresult.equals("Error")){
            answer_tv.setText(finalresult);
        }
    }
    String result(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0")) {
                finalresult.replace(".0", "");
            }
            return finalresult;
        }
        catch (Exception e){
            return "Error";
        }
    }
}