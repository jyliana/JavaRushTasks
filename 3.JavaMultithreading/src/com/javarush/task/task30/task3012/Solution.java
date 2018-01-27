package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        String[] line = new StringBuffer(Integer.toString(number, 3)).reverse().toString().split("");
        int[] array = new int[line.length + 1];
        System.arraycopy(Arrays.stream(line).mapToInt(Integer::parseInt).toArray(), 0, array, 0, line.length);
        StringBuilder result = new StringBuilder().append(number).append(" =");
        String[] formats = {" - %d", "", " + %d"};

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 2) {
                array[i] = -1;
                array[i + 1]++;
            } else if (array[i] == 3) {
                array[i] = 0;
                array[i + 1]++;
            } else if (array[i] == -2) {
                array[i] = 1;
                array[i + 1]--;
            } else if (array[i] == -3) {
                array[i] = 0;
                array[i + 1]--;
            }
            result.append(String.format(formats[array[i] + 1], (int) Math.pow(3, i)));
        }
        System.out.println(result);
    }
}