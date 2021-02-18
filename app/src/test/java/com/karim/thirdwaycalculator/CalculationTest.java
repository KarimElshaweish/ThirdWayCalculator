package com.karim.thirdwaycalculator;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


public class CalculationTest {


    @Test
     public void checkIfTheResultIsValid(){
        Double result=Calculation.evaluation(null,"1+2+3");
        Double realValue=1.0+2.0+3.0;
        assertThat(result).isEqualTo(realValue);
    }

    @Test
    public void checkingIfTheCharacterIsNumber(){
        boolean result=Calculation.checkIfIsItAAnumber('5');
        assertThat(result).isTrue();
    }
}