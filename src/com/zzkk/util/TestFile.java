package com.zzkk.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

public class TestFile implements Callable<Boolean> {
    private String email;
    private String format;
    private String text;

    public TestFile(String email ,String language ,String text){
        switch (language){
            case "c" : this.format = email+".c";break;

            case "c++" : this.format = email+".cpp";break;

            case "java" : this.format = "Main.java";break;

            default:return;
        }
        this.email = email;
        this.text = text;
    }

    @Override
    public Boolean call() throws IOException {
        if(format == null || format.equals(""))
            return false;
        File file = new File("S:/OnlineJudge/"+email+"/"+format);
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] bytes = text.getBytes();
        outputStream.write(bytes);
        System.out.println("save success");
        outputStream.close();
        return true;
    }
}
