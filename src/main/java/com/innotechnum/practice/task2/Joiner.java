package com.innotechnum.practice.task2;

import java.util.*;

public class Joiner {
    public static ArrayList<Element> elementsInArrayList1 = new ArrayList<>();
    public static ArrayList<Element> elementsInArrayList2 = new ArrayList<>();

    public static LinkedList<Element> elementsInLinkedList1 = new LinkedList<>();
    public static LinkedList<Element> elementsInLinkedList2 = new LinkedList<>();

    public static HashMap<Integer, List<String>> elementsInHashMap1 = new HashMap<>();
    public static HashMap<Integer, List<String>> elementsInHashMap2 = new HashMap<>();

    public static void summary() {
        System.out.println("Из первого файла записано " + elementsInArrayList1.size() + " элементов");
        System.out.println("Из второго файла записано " + elementsInArrayList2.size() + " элементов");
    }

    public static void innerJoinWithArrayList(String file1, String file2) {
        if ((elementsInArrayList1 = IOElements.readInArrayList(file1)).isEmpty()) {
            System.out.println("Данные в файле - " + file1 + " некорректны");
            return;
        } else if ((elementsInArrayList2 = IOElements.readInArrayList(file2)).isEmpty()) {
            System.out.println("Данные в файле - " + file2 + " некорректны");
            return;
        }

        System.out.println("Результат соединения ArrayList");
        IOElements.printElements(innerJoin(elementsInArrayList1, elementsInArrayList2));
    }

    public static ArrayList<CombinedElement> innerJoin(ArrayList<Element> elements1, ArrayList<Element> elements2) {
        ArrayList<CombinedElement> combinedElements = new ArrayList<>();
        for (Element element1 : elements1) {
            for (Element element2 : elements2)
                if (element1.getId() == element2.getId()) {
                    combinedElements.add(new CombinedElement(element1.getId(), element1.getValue(),
                            element2.getValue()));
                }
        }
        return combinedElements;
    }

    public static void innerJoinWithLinkedList() {
        if ((elementsInArrayList1.isEmpty()) || (elementsInArrayList2.isEmpty())) {
            System.out.println("Данные из файлов не прочитаны");
            return;
        } else {
            passElements(elementsInArrayList1, elementsInLinkedList1);
            passElements(elementsInArrayList2, elementsInLinkedList2);
        }

        System.out.println("Результат соединения отсортированный LinkedList");
        IOElements.printElements(innerJoin(elementsInLinkedList1, elementsInLinkedList2));
    }

    public static LinkedList<CombinedElement> innerJoin(LinkedList<Element> elements1, LinkedList<Element> elements2) {
        LinkedList<CombinedElement> combinedElements = new LinkedList<>();
        ListIterator<Element> iterator1 = elements1.listIterator();
        ListIterator<Element> iterator2 = elements2.listIterator();

        Element element1 = iterator1.next();
        Element element2 = iterator2.next();

        while (true) {
            try {
                if (element1.getId() > element2.getId()) {
                    element2 = iterator2.next();
                } else if (element1.getId() < element2.getId()) {
                    while (element2.getId() >= element1.getId()) {
                        if (iterator2.hasPrevious()) {
                            element2 = iterator2.previous();
                        } else {
                            break;
                        }
                    }
                    element2 = iterator2.next();

                    element1 = iterator1.next();
                } else {
                    combinedElements.add(new CombinedElement(element1.getId(), element1.getValue(),
                            element2.getValue()));
                    if (iterator2.hasNext()) {
                        element2 = iterator2.next();
                    } else {
                        element1 = iterator1.next();
                    }

                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return combinedElements;
    }

    public static void innerJoinWithHashMap() {
        if ((elementsInArrayList1.isEmpty()) || (elementsInArrayList2.isEmpty())) {
            System.out.println("Данные из файлов не прочитаны");
            return;
        } else {
            passElements(elementsInArrayList1, elementsInHashMap1);
            passElements(elementsInArrayList2, elementsInHashMap2);
        }

        System.out.println("Результат соединения HashMap");
        IOElements.printElements(innerJoin(elementsInHashMap1, elementsInHashMap2));
    }

    public static HashMap<Integer, List<List<String>>> innerJoin(Map<Integer, List<String>> elements1, Map<Integer,
            List<String>> elements2) {
        HashMap<Integer, List<List<String>>> combinedElements = new HashMap<>();
        for (Map.Entry<Integer, List<String>> entry : elements1.entrySet()) {
            if (elements2.get(entry.getKey()) != null) {
                List<List<String>> values = new ArrayList<>();
                values.add(entry.getValue());
                values.add(elements2.get(entry.getKey()));
                combinedElements.put(entry.getKey(), values);
            }
        }
        return combinedElements;
    }

    public static void passElements(ArrayList<Element> elementsInArrayList,
                                    Map<Integer, List<String>> elementsInHashMap) {
        for (Element element : elementsInArrayList) {
            if (elementsInHashMap.get(element.getId()) == null) {
                elementsInHashMap.put(element.getId(), new ArrayList<String>(Arrays.asList(element.getValue())));
            } else {
                elementsInHashMap.get(element.getId()).add(element.getValue());
            }
        }
    }

    public static void passElements(ArrayList<Element> elementsInArrayList, LinkedList<Element> elementsInLinkedList) {
        elementsInLinkedList.addAll(elementsInArrayList);
        Collections.sort(elementsInLinkedList);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Некорректные параметры программы. Должно быть два входных текстовыйх файла");
            return;
        }

        innerJoinWithArrayList(args[0], args[1]);
        summary();

        System.out.println();
        innerJoinWithLinkedList();
        System.out.println();
        innerJoinWithHashMap();
    }

}
