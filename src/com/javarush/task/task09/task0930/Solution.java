package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
Задача по алгоритмам Ӏ Java Syntax: 9 уровень, 11 лекция
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String line = reader.readLine();
        while (!line.isEmpty()) {
            list.add(line);
            line = reader.readLine();
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : array) {
            if (isNumber(s))
                integers.add(Integer.parseInt(s));
            else strings.add(s);
        }
        Collections.sort(integers);
        Collections.reverse(integers);
        Set<String> set = new HashSet<>(strings);
        Set<String> setCopy = new HashSet<>(set);
        for (int i = 0; i < set.size(); i++) {
            String t = null;
            for (String s : setCopy){
                if (t == null) {
                    t = s;
                }
                for (String c : setCopy) {
                    if (isGreaterThan(t, c)) {
                        t = c;
                    }
                }
            }
            setCopy.remove(t);
            strings.set(i, t);
        }
        int s = 0, i = 0;
        for (int j = 0; j < array.length; j++) {
            if (isNumber(array[j])) {
                array[j] = String.valueOf(integers.get(i));
                i++;
            }
            else {
                array[j] = strings.get(s);
                s++;
            }
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String text) {
        if (text.length() == 0) {
            return false;
        }

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];

            // есть '-' внутри строки
            if (i != 0 && character == '-') {
                return false;
            }

            // не цифра и не начинается с '-'
            if (!Character.isDigit(character) && character != '-') {
                return false;
            }

            // одиночный '-'
            if (chars.length == 1 && character == '-') {
                return false;
            }
        }

        return true;
    }
}