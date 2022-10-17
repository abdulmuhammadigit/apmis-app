package com.clean.common.utility;

public class CodeGenerator {
    public static String generate(String prefix,String code,int year, int sequence) {
        StringBuilder stringBuilder = new StringBuilder();
        if (prefix != null && !prefix.isEmpty())
            stringBuilder.append(prefix + "-");

        stringBuilder.append(code);
        stringBuilder.append("-"+year);
        stringBuilder.append("-" + "000000");
        String strSequence = Integer.toString(sequence);
        int lenght = strSequence.length();
        String result = stringBuilder.toString().replaceFirst(".{" + lenght + "}$", strSequence);
        return result;
    }
}
