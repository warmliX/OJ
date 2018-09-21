package com.zzkk.util;

import java.io.IOException;
import java.util.concurrent.Callable;

public interface IExecutor extends Callable<String> {
    public String Execute() throws IOException, InterruptedException;

    public String compile() throws Exception;

    public void saveParamter()throws IOException;

    public String is_Compile(Process process) throws Exception;

}
