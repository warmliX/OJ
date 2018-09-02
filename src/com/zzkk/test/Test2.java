package com.zzkk.test;

import java.util.ArrayList;
import java.util.List;

public class Test2{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(1000000);
        int i=0;
        while(i++<1000000){
            list.add(i+"test1234567890");
        }
    }
}