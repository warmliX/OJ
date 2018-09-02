package com.zzkk.test;

import java.io.IOException;

public class Test{

    public static void print() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /c S: && cd Java内存测试 && java Son");
        while(true){
            long total = Runtime.getRuntime().totalMemory();
            long used  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println(runtime.totalMemory()-runtime.freeMemory());
            Thread.sleep(1000);
        }


    }

    public static void main(String[] args)throws Exception {
        Runtime runtime = Runtime.getRuntime();
        long total  = runtime.totalMemory();
        long free = runtime.freeMemory();
        long Max = runtime.maxMemory();
        System.out.println(total-free);
        Process process = Runtime.getRuntime().exec("");
        System.out.println(free);
        System.out.println(Max);
    }
}