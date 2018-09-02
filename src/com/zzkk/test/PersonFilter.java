package com.zzkk.test;

import java.util.ArrayList;
import java.util.List;

public class PersonFilter {
    public static void main(String[] args){
        ArrayList<Person> list = new ArrayList<>(10);
        list.add(new Person(100,100));
        list.add(new Person(100,80));
        list.add(new Person(100,102));
        list.add(new Person(100,90));
        list.add(new Person(100,101));
        list.add(new Person(100,120));
        ArrayList<Person> data = (ArrayList<Person>) filter(list ,(Person person)->person.getWeight()>100?"weight":"light");
        for (Person person:data
             ) {
            System.out.println(person.getWeight());
        }
    }

    public static List<Person> filter(List<Person> p ,PresonFormat pf){
        ArrayList<Person> list = new ArrayList<>();
        for (Person person:p) {
            if(pf.test(person).equals("weight")){
                list.add(person);
            }
        }
        return list;
    }
}
