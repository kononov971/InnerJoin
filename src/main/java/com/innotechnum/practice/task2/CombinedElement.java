package com.innotechnum.practice.task2;

public class CombinedElement extends Element {
    private String additionalValue;

    public CombinedElement(int id, String value, String additionalValue) {
        super(id, value);
        this.additionalValue = additionalValue;
    }

    public String getAdditionalValue() {
        return additionalValue;
    }
}
