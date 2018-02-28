package com.javarush.task.task34.task3404;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public Solution() {
        //don't delete
    }

    public static void main(String[] args) throws ScriptException {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation) throws ScriptException {
        //implement
        String line = expression;
        String tmp = "";
        boolean isTrig = false;
        String tmp_line = expression;
        String tme = "";

        String[] token = expression.split("[^+\\-\\/\\*\\^]");
        String[] token2 = expression.split("[^sincostan]");
        int count = 0;
        if (count != countOperation && countOperation != 0)
            count = countOperation;
        else {
            for (int i = 0; i < token.length; i++) {
                if (!token[i].isEmpty()) {
                    count++;
                }
            }
            for (int i = 0; i < token2.length; i++) {
                if (!token2[i].isEmpty()) {
                    count++;
                }
            }
        }

        String[] items = {"sin", "cos", "tan", "^"};
        if (!expression.matches("-?\\d+(\\.\\d+)?")) {
            if (expression.contains("(")) {
                if (expression.indexOf("(") != 0) {
                    tme = expression.substring(expression.lastIndexOf("(") - 1);
                } else {
                    tme = expression.substring(expression.lastIndexOf("("));
                }
                line = tme.substring(0, tme.indexOf(")") + 1);

                if ((tme.startsWith("n") || tme.startsWith("s")) && expression.substring(0, expression.indexOf("(")).length() >= 3 && !line.contains("^")) {
                    tme = expression.substring(expression.lastIndexOf("(") - 3);
                    line = tme.substring(0, tme.indexOf(")") + 1);
                    if (Arrays.stream(items).parallel().anyMatch(line::contains)) {
                        isTrig = true;
                        if (line.substring(line.lastIndexOf("(") + 1, line.indexOf(")")).matches("-?\\d+(\\.\\d+)?")) {
                            isTrig = false;
                        } else line = expression.substring(expression.lastIndexOf("("), expression.indexOf(")") + 1);
                    }
                } else {
                    line = line.substring(line.lastIndexOf("("), line.indexOf(")") + 1);
                }
            }
            if (line.contains("^")) {
                String[] tms = line.split("[(+/*)-]");
                for (String item : tms) {
                    if (item.contains("^")) {
                        line = item;
                        break;
                    }
                }
            }

            String trig = "";
            String calculate_line = line;

            String trigonometry = Arrays.stream(items).filter(calculate_line::contains).findAny().orElse("");

            if (!trigonometry.isEmpty()) {
                if (calculate_line.contains("^")) {
                    trig = calculate_line;
                } else
                    trig = calculate_line.substring(calculate_line.lastIndexOf("(") + 1, calculate_line.indexOf(")"));

                switch (trigonometry) {
                    case "sin":
                        tmp = String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(trig))));
                        break;
                    case "cos":
                        tmp = String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(trig))));
                        break;
                    case "tan":
                        tmp = String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(trig))));
                        break;
                    case "^":
                        tmp = String.valueOf(Math.pow(Double.parseDouble(calculate_line.split("[(^)]")[0]), Double.parseDouble(calculate_line.split("[(^)]")[1])));
                        break;
                }
            } else {
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                calculate_line = calculate_line.replace("--", "+");
                if (calculate_line.contains("(")) {
                    calculate_line = calculate_line.substring(calculate_line.lastIndexOf("(") + 1, calculate_line.indexOf(")"));
                }
                tmp = engine.eval(calculate_line).toString();
                if (tmp.contains("E")) {
                    tmp = String.valueOf(new BigDecimal(Double.parseDouble(engine.eval(calculate_line).toString())).setScale(6, RoundingMode.HALF_UP));
                }
            }

            if (isTrig) {
                tmp_line = expression.replace(line.substring(line.lastIndexOf("(") + 1, line.indexOf(")")), tmp);
            } else {
                tmp_line = expression.replace(line, tmp);
            }
            recursion(tmp_line, count);

        } else {
            double result = Double.parseDouble(expression);
            result = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (String.valueOf(result).endsWith("0")) {
                System.out.println((int) (result) + " " + count);
            } else {
                System.out.println(result + " " + count);
            }
        }
        return;
    }
}
