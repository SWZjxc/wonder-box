package com.wonderbox.engine;

import java.io.File;
import java.util.Arrays;

/**
 * @author Ge Mingjia
 */
public class EngineBox {

    CodePretreatment codePretreatment = new CodePretreatment();
    CompileHandler compileHandler = new CompileHandler();
    InvokeHandler invokeHandler = new InvokeHandler();

    public void run() {
        File file = codePretreatment.javaCodePretreat("import java.util.Scanner;", "helloWorld();",
                "public static void helloWorld() {\n" +
                        "       Scanner scanner = new Scanner(System.in);\n" +
                        "       int number = scanner.nextInt();\n" +
                        "       String text = scanner.next();\n" +
                        "       System.out.println(\"Number: \" + number);\n" +
                        "       System.out.println(\"Text: \" + text);\n" +
                        "       System.out.println(\"测试输出\");\n" +
                        "   }");
        // 创建模拟输入的字符串
        String[] input = {"42\nHello World\n", "42\nHello World\n"};
        File classFile = compileHandler.javaCompileHandler(file);
        String[] outputs = invokeHandler.javaInvokeHandler(classFile, input);
        Arrays.stream(outputs).forEach(System.out::println);
    }

    public static void main(String[] args) {
        EngineBox engineBox = new EngineBox();
        engineBox.run();
    }

}