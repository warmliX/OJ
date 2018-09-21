package com.zzkk.util;


import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ContExecutor implements IExecutor {

    private String format;

    private String UserFile;

    private String exeFile;

    private String testFile;

    private String memFile;

    private String timFile;

    private String paramter;

    public ContExecutor(String language ,String paramter ,String UserFile){
        this.UserFile = UserFile;
        this.paramter = paramter;
        this.exeFile = "/OnlineJudge/"+UserFile+"/"+UserFile;
        this.testFile = "/OnlineJudge/"+UserFile+"/test"+UserFile+".txt";
        this.memFile = "/OnlineJudge/"+UserFile+"/mem"+UserFile+".txt";
        this.timFile = "/OnlineJudge/"+UserFile+"/tim"+UserFile+".txt";
        switch (language){
            case "c":this.format = ".c"; break;
            case "c++":this.format = ".cpp"; break;
            case "java":this.format = ".java"; break;
        }
    }

    @Override
    public String Execute() throws IOException, InterruptedException {
        String mem_safe = null;
        if(format.equals(".java")){
            mem_safe = "/OnlineJudge/java_oj/mem_safe\n";
        }else{
            mem_safe = "/OnlineJudge/c_oj/mem_safe\n";
        }
        Process process = Runtime.getRuntime().exec("/usr/bin/sh");
        OutputStream stream = process.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        writer.write(mem_safe);
        writer.write(exeFile+"\n");
        writer.write(testFile+"\n");
        writer.write(timFile+"\n");
        writer.write(memFile+"\n");
        writer.close();

        boolean isExe = process.waitFor(10 ,TimeUnit.SECONDS);
        if(!isExe){
            Process kill = Runtime.getRuntime().exec("pkill -9 "+UserFile.substring(0,14));

            FileOutputStream sss = new FileOutputStream(new File(testFile));
            byte[] bytes = UserFile.substring(0,14).getBytes();
            sss.write(bytes);
            sss.close();

            return "out of running time";
        }else{
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }
            return stringBuffer.toString();
        }
    }

    @Override
    public String compile() throws Exception {
        String action = "";
        switch (format){
            case ".c":action="gcc"; break;
            case ".cpp":action="g++"; break;
            case ".java":action="javac"; break;
            default:return "language error";
        }

        Process process = null;
        if(format.equals(".java")){
            process = Runtime.getRuntime().exec(action + " " + "/OnlineJudge/"+UserFile+"/Main.java");
        }else {
            process = Runtime.getRuntime().exec(action + " " + exeFile + format + " -o " + exeFile);
        }
        boolean isCompile = process.waitFor(3,TimeUnit.SECONDS);
        if(!isCompile){
            return "compiling out of time";
        }
        String image = null;
        if((image=is_Compile(process))!=null){
            return image;
        }
        return null;
    }


    @Override
    public void saveParamter() throws IOException {
        File file = new File(timFile);
        if(!file.exists()){
            file.createNewFile();
        }

        file = new File(memFile);
        if(!file.exists()){
            file.createNewFile();
        }

        file = new File(testFile);
        if(!file.exists()){
            file.createNewFile();
        }

        FileOutputStream stream = new FileOutputStream(file);
        byte[] bytes = paramter.getBytes();
        stream.write(bytes);
        stream.close();
    }

    @Override
    public String is_Compile(Process process) throws Exception {
        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                BufferedReader br = null;
                br = new BufferedReader(new InputStreamReader(process.getErrorStream(),"utf-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                if(sb.length() > 0){
                    return sb.toString();
                }
                br.close();
                return null;
            }
        };

        return call.call();
    }

    @Override
    public String call() throws Exception {
        saveParamter();
        String compileImage = compile();
        if(compileImage!=null)
            return compileImage;

        String runImage = Execute();
        return runImage;
    }
}
