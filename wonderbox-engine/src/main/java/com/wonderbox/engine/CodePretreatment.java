package com.wonderbox.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Ge Mingjia
 * @date 2023/5/20
 */

public class CodePretreatment {

    /**
     * Java源代码的预处理，并写入文件中
     *
     * @param codeImport   Java的import语句
     * @param methodInvoke 方法调用语句
     * @param methodBody   方法本体
     * @return 返回生成的.java文件
     */
    public File javaCodePretreat(String codeImport, String methodInvoke, String methodBody) {
        Optional.ofNullable(codeImport).orElse("");
        // 输入的 java 源码
        String javaCode = String.format("%s;\n" +
                "public class Main {\n" +
                "   public static void main(String[] args) {\n" +
                "       long startTime = System.currentTimeMillis();\n" +
                "       %s ;\n" +
                "       long endTime = System.currentTimeMillis();\n" +
                "       long runningTime = endTime - startTime;\n" +
                "       System.out.println(\"丨\" + runningTime + \"ms\");\n" +
                "   }\n" +
                "   %s\n" +
                " }", codeImport, methodInvoke, methodBody);
        File javaFile = null;
        try {
            // 创建一个 .java 的临时文件
            javaFile = File.createTempFile("Main", ".java", new File("tempClass"));
            String fileName = javaFile.getName().replaceAll(".java", "");
            // 替换默认的类名 Main 为随机生成的类名
            String newJavaCode = javaCode.replaceAll("Main", fileName);
            try (FileWriter writer = new FileWriter(javaFile)) {
                writer.write(newJavaCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return javaFile;
    }

    public File javaCodePretreat(String methodInvoke, String methodBody) {
        return javaCodePretreat("", methodInvoke, methodBody);
    }
}
