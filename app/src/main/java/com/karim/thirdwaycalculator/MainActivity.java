package com.karim.thirdwaycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karim.thirdwaycalculator.databinding.ActivityMainBinding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static com.karim.thirdwaycalculator.Calculation.checkIfIsItAAnumber;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    String allCalc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        formulaAndResultStack=new Stack<>();
        formulaAndResultQueue=new Stack<>();
    }
    Stack<Pair<String,String>>formulaAndResultStack;
    Stack<Pair<String,String>>formulaAndResultQueue;
    @SuppressLint("SetTextI18n")
    private void addTextToViewText(String text){
            allCalc=allCalc+text;
            activityMainBinding.allCalculationText.setText(allCalc);
    }
    public void buttonOnClick(View view){
        String buttonText=((Button)view).getText().toString();
        if(checkIfIsItAAnumber(buttonText.toCharArray()[buttonText.length()-1])){
            //TODO
            addTextToViewText(buttonText);
        }else{
            switch (buttonText){
                case "C":
                    activityMainBinding.allCalculationText.setText("");
                    allCalc="";
                    activityMainBinding.result.setText("");
                    break;
                case "^":
                    addTextToViewText("^");
                    break;
                case "%":
                    addTextToViewText("%");
                    break;
                case "/":
                    addTextToViewText("/");
                    break;
                case "X":
                    addTextToViewText("*");
                    break;
                case "-":
                    addTextToViewText("-");
                    break;
                case "+":
                    addTextToViewText("+");
                    break;
                case "=":
                    equalsMethod();
                    break;
                case "undo":
                    undoFunction();
                    break;
                case "reundo":
                    reUndoFunction();
                    break;
            }
        }
    }

    private void reUndoFunction() {
        if(!formulaAndResultQueue.isEmpty()) {
            Pair<String, String> lastOperation = formulaAndResultQueue.pop();
            formulaAndResultStack.add(lastOperation);
            if (lastOperation.first.equals(allCalc))
                lastOperation = formulaAndResultQueue.pop();
            allCalc = lastOperation.first;
            activityMainBinding.result.setText(lastOperation.second);
            activityMainBinding.allCalculationText.setText(allCalc);
        }else{
            Toast.makeText(this,"No Operations finds",Toast.LENGTH_SHORT).show();
        }
    }

    private void undoFunction() {
        if(formulaAndResultStack.size()!=0) {
            Pair<String, String> lastOperation = formulaAndResultStack.pop();
            formulaAndResultQueue.add(lastOperation);
            if (lastOperation.first.equals(allCalc)&&formulaAndResultStack.size()>0) {
                lastOperation = formulaAndResultStack.pop();
                formulaAndResultStack.push(lastOperation);
            }
            else{
                reset();
            }
            allCalc = lastOperation.first;
            activityMainBinding.result.setText(lastOperation.second);
            activityMainBinding.allCalculationText.setText(allCalc);
        }else{
           reset();
        }

    }

    private void reset() {
        allCalc="";
        activityMainBinding.result.setText("");
        activityMainBinding.allCalculationText.setText("");
    }

    private void equalsMethod() {
        Double result=Calculation.evaluation(this,allCalc);
        if(result!=null)
            activityMainBinding.result.setText(String.valueOf(result));
        Pair<String,String>formulaAndResultPair=new Pair<>(allCalc,String.valueOf(result));
        formulaAndResultStack.push(formulaAndResultPair);
    }

}