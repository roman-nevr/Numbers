package ru.romaberendeev.numbers.utils;

import java.util.Date;
import java.util.Random;

import ru.romaberendeev.numbers.domain.entity.Quiz;

/**
 * Created by roma on 18.05.2016.
 */
public class Equation {
    public static int result;
    public static String expression;


    private static Random random = new Random(new Date().getTime());
    
    public static Quiz calc(int difficulty){
        int first;
        int second;
        int sign;

        int maxAddOperand = 10, maxMultiplyOperand = 10, maxDivisionOperand = 10, maxSign = 0;

        System.out.println("start calc quiz" + new Date().getTime());

        switch (difficulty){
            case 1:{
                maxAddOperand = 10;
                maxSign = 0;
                break;
            }
            case 2:{
                maxAddOperand = 10;
                maxSign = 1;
                break;
            }
            case 3:{
                maxAddOperand =100;
                maxSign = 1;
                break;
            }
            case 4:{
                maxAddOperand = 100;
                maxMultiplyOperand = 5;
                maxSign = 2;
                break;
            }
            case 5:{
                maxAddOperand = 100;
                maxMultiplyOperand = 10;
                maxSign = 2;
                break;
            }
            case 6:{
                maxAddOperand = 100;
                maxMultiplyOperand = 10;
                maxDivisionOperand = 10;
                maxSign = 3;
                break;
            }
            default:{

            }
        }

        sign = random.nextInt(maxSign + 1);

        switch (sign){

            case 0:{// " + "
                first = random.nextInt(maxAddOperand) + 1;
                second = random.nextInt(maxAddOperand) + 1;
                expression = "" + first+" + " + second + " = ???";
                result = first + second;
                break;
            }
            case 1:{// " - "
                do{
                    first = random.nextInt(maxAddOperand) + 1;
                    second = random.nextInt(maxAddOperand) + 1;
                    result = first - second;
                } while (result <= 0);
                expression = "" + first+" - " + second + " = ???";
                break;
            }
            case 2: {// " * "
                first = random.nextInt(maxMultiplyOperand) + 1;
                second = random.nextInt(maxMultiplyOperand) + 1;
                expression = "" + first+" * " + second + " = ???";
                result = first * second;
                break;
            }
            case 3: {// " / "
                result = random.nextInt(maxDivisionOperand) + 1;
                second = random.nextInt(maxDivisionOperand) + 1;
                first = result * second;
                expression = "" + first + " / " + second + " = ???";
                break;
            }
        }

        System.out.println("finish calc quiz" + new Date().getTime());
        return Quiz.create(expression, result);
        /*Log.d("myLog",expression+" "+result);

        for (int i = 0; i < 40; i++) {
            Log.d("myLog", "" + random.nextInt(10));

        }*/
    }
}
