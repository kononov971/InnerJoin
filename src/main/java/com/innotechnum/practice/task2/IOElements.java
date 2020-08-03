package com.innotechnum.practice.task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IOElements {
    public static ArrayList<Element> readInArrayList(String file) {
        ArrayList<Element> elements = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            int id;
            String value;
            Element element;

            while((currentLine = bufferedReader.readLine()) != null) {
                String[] information = currentLine.split(",");

                if (!Element.checkElement(information)) {
                    continue;
                }

                id = Integer.valueOf(information[0]);
                value = information[1];

                element = new Element(id, value);
                elements.add(element);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл " + file + " не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла " + file);
        }

        return elements;
    }



    public static void printElements(List<CombinedElement> elements) {
        for (CombinedElement element : elements) {
            System.out.println(element.getId() + " " + element.getValue() + " " + element.getAdditionalValue());
        }
    }

    public static void printElements(Map<Integer, List<List<String>>> elements) {
        List<String> valuesOfFile1;
        List<String> valuesOfFile2;
        for (Map.Entry<Integer, List<List<String>>> entry : elements.entrySet()) {
            valuesOfFile1 = entry.getValue().get(0);
            valuesOfFile2 = entry.getValue().get(1);

            for(String value1 : valuesOfFile1){
                for (String value2 : valuesOfFile2) {
                    System.out.println(entry.getKey() + " " + value1 + " " + value2);
                }
            }
        }
    }
}
