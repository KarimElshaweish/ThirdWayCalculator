package com.karim.thirdwaycalculator;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public  class Calculation {
    static ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

    /**
     * This method is used to evaluate the given formula from the
     * second parameter. return a double variable result.
     *
     * @param context This is the first parameter to evaluation method
     * @param formula This is the second parameter to evaluation method
     * @return Double this returns the result of the given formula
     */
    public static Double evaluation(Context context, String formula) {
        Double result = null;
        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            if (context != null)
                Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show();
            else
                Log.e("Error", "Invalid Input");
        }
        return result;
    }

    /**
     * This method is used if....
     * .. the character in the first parameter is number or not
     *
     * @param c the first parameter to check if the character is a number
     * @return return a boolean value of the checking
     */
    public static boolean checkIfIsItAAnumber(char c) {
        return (c <= '9' && c >= '0') || c == '.';
    }
}