package com.zzkk.test;

public class qunwork {
    public static void main(String[] args)throws Exception{
        char[] a = {'1','2','3','4','5'};
        char[] b=reversal(a,1,2);
        for(int i = 0 ; i < b.length ; i++)
            System.out.println(b[i]);
    }

    public static char[] reversal(char str[] ,int begin ,int end) throws Exception {
        if(str == null)
            throw new NullPointerException();
        if(str.length == 0)
            throw new Exception();
        if(end < begin)
            throw new Exception();
        if(str.length <= begin)
            throw new IndexOutOfBoundsException();
        if(end == str.length-1)
            return str;
        char[] nStr = new char[str.length];

        if(begin == 0){
            for(int i = end+1, j=0 ; i < str.length ; i++ ,j++){
                nStr[j] = str[i];
            }

            for(int i = str.length-(end-begin+1) ,j=begin ; i < str.length && j<=end ; i++,j++){
                nStr[i] = str[j];
            }

            return nStr;
        }

        for(int i=0 ;i<begin ;i++){
            nStr[i] = str[i];
        }

        for(int i=end+1,j=begin ;i<str.length ;i++,j++){
            nStr[j] = str[i];
        }

        for(int i=str.length-(end-begin+1),j=begin ;i<str.length ;i++,j++){
            nStr[i] = str[j];
        }

        return nStr;
    }
}
