package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
*/
public class Solution {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(tree.isEmpty());
        }
    }
}
