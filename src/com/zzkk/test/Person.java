package com.zzkk.test;

public class Person {
    private int high;
    private int weight;

    public Person(int high ,int weight){
        this.weight = weight;
        this.high = high;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
