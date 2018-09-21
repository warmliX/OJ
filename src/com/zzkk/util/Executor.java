package com.zzkk.util;

import com.zzkk.dao.impl.QuestionDaoFactory;
import com.zzkk.dao.impl.Test_dataDao;
import com.zzkk.dao.impl.UpdateStateDao;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Executor implements Runnable {
    private String email;
    private String format;
    private String text;
    private int rid;
    private int qid;

    public Executor(int qid ,String email ,String language ,String text ,int rid){
        switch (language){
            case "c" : this.format = ".c";break;

            case "c++" : this.format = ".cpp";break;

            case "java" : this.format = ".java";break;

            default:return;
        }
        this.email = email;
        this.text = text;
        this.rid = rid;
        this.qid = qid;
    }


    @Override
    public void run() {
        int isCompile = compile();
        UpdateStateDao dao = new UpdateStateDao(String.valueOf(isCompile) ,0 ,0 ,rid);
        try {
            dao.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isCompile == 1){
            try {
                int[] is_exe = Exec();
                dao = new UpdateStateDao(String.valueOf(is_exe[0]) ,is_exe[1] ,is_exe[2] ,rid);
                dao.update();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int compile(){

        Runtime runtime = Runtime.getRuntime();
        try {
            if(format.equals(".c")){
                Process process = runtime.exec("gcc /OnlineJudge/"+email+"/"+email+format+" -o /OnlineJudge/"+email+"/c"+qid+email);
                Thread.sleep(3000);
                if(process.isAlive()){
                    process.destroy();
                    return 2;
                }
                if(!is_Compile(process)){
                    return 3;
                }
            }
            else if(format.equals(".cpp")){
                Process process = runtime.exec("g++ /OnlineJudge/"+email+"/"+email+format+" -o /OnlineJudge/"+email+"/cp"+qid+email);
                Thread.sleep(3000);
                if(process.isAlive()){
                    process.destroy();
                    return 2;
                }
                if(!is_Compile(process)){
                    return 3;
                }
            }
            else {
                Process process = runtime.exec("javac /OnlineJudge/"+email+"/Main.java");
                Thread.sleep(3000);
                if(process.isAlive()){
                    process.destroy();
                    return 2;
                }
                if(!is_Compile(process)){
                    return 3;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public boolean is_Compile(Process process) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(process.getErrorStream(),"GB2312"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        System.out.println(sb.toString());
        System.out.println(sb.length());
        if(sb.length() > 0){
            return false;
        }
        br.close();
        return true;
    }

    public int[] Exec() throws SQLException, IOException, InterruptedException {
        int time = 0,mem = 0;
        QuestionDaoFactory factory = new QuestionDaoFactory();
        Test_dataDao dao = factory.creatTest_dataDao(qid);
        ArrayList<String[]> data = dao.sel();
        Runtime runtime = Runtime.getRuntime();
        if(format.equals(".java")){
            String exeFile = "/OnlineJudge/"+email+"/Main";
            String testFile = "/OnlineJudge/"+email+"/jav"+qid+".txt";
            String timeFile = "/OnlineJudge/"+email+"/jav"+qid+"tim.txt";
            String memFile = "/OnlineJudge/"+email+"/jav"+qid+"mem.txt";
            File file = new File(testFile);
            if(!file.exists()){
                file.createNewFile();
            }
            file = new File(timeFile);
            if(!file.exists()){
                file.createNewFile();
            }
            file = new File(memFile);
            if(!file.exists()){
                file.createNewFile();
            }

            for(int i = 0 ;i < data.size() ;i++){
                saveTest(testFile ,data.get(i)[0]);
                Process process = runtime.exec("/OnlineJudge/java_oj/mem_time > /OnlineJudge/end.txt");
                OutputStream stream = process.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
                writer.write(exeFile+"\n");
                writer.write(testFile+"\n");
                writer.write(timeFile+"\n");
                writer.write(memFile+"\n");
                writer.close();
                boolean isExe = process.waitFor(10 ,TimeUnit.SECONDS);
                if(isExe) {
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = "";
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    while ((line = reader.readLine()) != null){
                        stringBuffer.append(line);
                    }
                    System.out.println(stringBuffer.toString());
                    if(!stringBuffer.toString().equals(data.get(i)[1])){
                        return new int[]{5 ,0 ,0};
                    }
                } else{
                    process.destroy();
                    return new int[]{4 ,0 ,0};
                }
            }
        } else if(format.equals(".c")){
            String exeFile = "/OnlineJudge/"+email+"/c"+qid+email;
            String testFile = "/OnlineJudge/"+email+"/c"+qid+"test.txt";
            String timeFile = "/OnlineJudge/"+email+"/c"+qid+"tim.txt";
            String memFile = "/OnlineJudge/"+email+"/c"+qid+"mem.txt";
            File file = new File(testFile);
            if(!file.exists()){
                file.createNewFile();
            }
            file = new File(timeFile);
            if(!file.exists()){
                file.createNewFile();
            }
            file = new File(memFile);
            if(!file.exists()){
                file.createNewFile();
            }

            for(int i = 0 ;i < data.size() ;i++){
                saveTest(testFile ,data.get(i)[0]);
                Process process = runtime.exec("/usr/bin/sh");
                OutputStream stream = process.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
                writer.write("/OnlineJudge/c_oj/mem_safe\n");
                writer.write(exeFile+"\n");
                writer.write(testFile+"\n");
                writer.write(timeFile+"\n");
                writer.write(memFile+"\n");
                writer.close();
                boolean isExe = process.waitFor(10 ,TimeUnit.SECONDS);
                if(isExe) {
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = "";
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    while ((line = reader.readLine()) != null){
                        stringBuffer.append(line);
                    }
                    reader.close();
                    int memIndex = stringBuffer.indexOf("mem=");
                    int timeIndex = stringBuffer.indexOf("time=");
                    if(memIndex>0 & timeIndex>0) {
                        String outcome = stringBuffer.toString().substring(0, memIndex);
                        mem += Integer.parseInt(stringBuffer.substring(memIndex + 4, timeIndex));
                        time += Integer.parseInt(stringBuffer.substring(timeIndex + 5, stringBuffer.length()));

                        if (!outcome.replace(" " ,"").replace("\n" ,"").replace("\r" ,"").
                                toString().equals(data.get(i)[1].replace(" " ,""))) {
                            return new int[]{5, 0, 0};
                        }
                    }else{
                        return new int[]{5 ,0 ,0};
                    }
                } else{
                    System.out.print("destory");
                    process.destroyForcibly();
                    System.out.print(process.isAlive());
                    return new int[]{4 ,0 ,0};
                }
            }
        } else{
            String exeFile = "/OnlineJudge/"+email+"/cp"+qid+email;
            String testFile = "/OnlineJudge/"+email+"/cp"+qid+"test.txt";
            String timeFile = "/OnlineJudge/"+email+"/cp"+qid+"tim.txt";
            String memFile = "/OnlineJudge/"+email+"/cp"+qid+"mem.txt";
            File file = new File(testFile);
            if(!file.exists()){
                file.createNewFile();
            }
            file = new File(timeFile);
            if(!file.exists()){
                file.createNewFile();
            }
            file = new File(memFile);
            if(!file.exists()){
                file.createNewFile();
            }

            for(int i = 0 ;i < data.size() ;i++){
                saveTest(testFile ,data.get(i)[0]);
                Process process = runtime.exec("/usr/bin/sh");
                OutputStream stream = process.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));
                bw.write("/OnlineJudge/c_oj/mem_safe>/OnlineJudge/end.txt\n");
                bw.write(exeFile+"\n");
                bw.write(testFile+"\n");
                bw.write(timeFile+"\n");
                bw.write(memFile+"\n");
                bw.close();
                stream.close();
                boolean isExe = process.waitFor(10 ,TimeUnit.SECONDS);
                if(isExe) {
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = "";
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    while ((line = reader.readLine()) != null){
                        stringBuffer.append(line);
                    }
                    reader.close();
                    int memIndex = stringBuffer.indexOf("mem=");
                    int timeIndex = stringBuffer.indexOf("time=");
                    if(memIndex>0 & timeIndex>0) {
                        String outcome = stringBuffer.toString().substring(0, memIndex);
                        mem += Integer.parseInt(stringBuffer.substring(memIndex + 4, timeIndex));
                        time += Integer.parseInt(stringBuffer.substring(timeIndex + 5, stringBuffer.length()));
                        if (!outcome.replace(" " ,"").replace
                                ("\n" ,"").replace("\r" ,"").
                                equals(data.get(i)[1].replace(" " ,""))) {
                            return new int[]{5, 0, 0};
                        }
                    }else{
                        return new int[]{5 ,0 ,0};
                    }
                } else{
                    process.destroy();
                    return new int[]{4 ,0 ,0};
                }
            }
        }
        return new int[]{6 ,time/data.size() ,mem/data.size()};
    }

    public void saveTest(String testFile ,String paramter) throws IOException {
        File file = new File(testFile);
        FileOutputStream stream = new FileOutputStream(file);
        byte[] bytes = paramter.getBytes();
        stream.write(bytes);
        stream.close();
    }
}
