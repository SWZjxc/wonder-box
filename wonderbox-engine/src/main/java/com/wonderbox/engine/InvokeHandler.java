package com.wonderbox.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ge Mingjia
 * @date 2023/5/20
 */
public class InvokeHandler {

    // 反射方法缓存
    private static final Map<String, Method> METHOD_CACHE = new HashMap<>();
    // 输出数组
    private static final List<String> OUTPUT_LIST = new ArrayList<>();

    /**
     * 反射调用编译后的.class文件中的main方法
     *
     * @param classFile .class文件
     */
    public String[] javaInvokeHandler(File classFile, String[] input) {
        if (classFile == null) {
            throw new IllegalStateException("ClassFile is null!");
        }
        String className = classFile.getName().replaceAll(".java", "");
        try {
            // 获取缓存的方法对象，如果不存在则进行反射获取并缓存
            Method mainMethod = METHOD_CACHE.get(className);
            if (mainMethod == null) {
                // 用 URLClassLoader 加载包含 Main.class 文件的目录
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{
                        classFile.getParentFile().toURI().toURL()
                });
                Class<?> clazz = Class.forName(className, true, classLoader);
                mainMethod = clazz.getMethod("main", String[].class);
                // 设置方法对象为可访问
                mainMethod.setAccessible(true);
                // 将方法对象添加到缓存中
                METHOD_CACHE.put(className, mainMethod);
            }
            for (String inputData : input) {
                // 将System.in重定向到模拟输入的字符串
                System.setIn(new ByteArrayInputStream(inputData.getBytes()));
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream originalPrintStream = System.out;
                System.setOut(new PrintStream(outputStream));
                mainMethod.invoke(null, (Object) new String[]{});
                // 将输出结果添加到outputList
                OUTPUT_LIST.add(outputStream.toString());
                // 还原System.out
                System.setOut(originalPrintStream);
            }
            return OUTPUT_LIST.toArray(new String[0]);
        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalStateException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
