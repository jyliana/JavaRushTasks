package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public static Solution[] getTwoSolutions() {
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        for (int i = 0; i < 2; i++) {
            solution1.innerClasses[i] = solution1.new InnerClass();
            solution2.innerClasses[i] = solution2.new InnerClass();
        }
        return new Solution[]{solution1, solution2};
    }

    public static void main(String[] args) {

    }

    public class InnerClass {
    }
}
