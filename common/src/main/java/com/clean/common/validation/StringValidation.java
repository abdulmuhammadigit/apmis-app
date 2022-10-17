package com.clean.common.validation;

public class StringValidation {
    public static boolean IsEmpty(String str){
        return str.isEmpty();
    }

    public static boolean IsNull(String str){
        return str == null;
    }

    public static boolean IsNullOrEmpty(String str){
        return IsNull(str) || IsEmpty(str);
    }

    public static String IsNullOrEmpty(String str,String replace){
        return IsNullOrEmpty(str) ? replace : str;
    }

}
