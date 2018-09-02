package com.zzkk.util;

import java.io.File;

public class UserDir {
    public static void mkdir(String email){
        File file = new File("S:/OnlineJudge/"+email);
        if(!file.exists() || !file.isDirectory()){
            file.mkdir();
        }
    }
}
