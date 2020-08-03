package com.innotechnum.practice.task2;

import java.util.Arrays;

public class Element implements Comparable<Element> {
    private int id;
    private String value;

    public Element (int id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public int compareTo(Element element) {
        int result;
        result = this.id -element.getId();
        if (result != 0) {
            return  result;
        }
        result = this.value.compareTo(element.getValue());
        return result;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }


    public static boolean checkElement(String[] information) {
        if (information.length != 2) {
            System.out.println("В строке - " + Arrays.toString(information) + "некорректное количество элементов");
            return false;
        }

        try {
            int id = Integer.valueOf(information[0]);
            if (id <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный id в строке - " + Arrays.toString(information));
            return false;
        }

        if(information[1].trim().isEmpty()) {
            System.out.println("Некорректный value в строке - " + Arrays.toString(information));
            return false;
        }

        return true;
    }
}
