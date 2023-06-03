package com.wonderbox.engine;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.Objects;

/**
 * @author Ge Mingjia
 * @date 2023/5/20
 */
public class CompileHandler {

    /**
     * 编译java文件
     *
     * @param javaFile java代码文件
     * @return 编译后的.class文件
     */
    public File javaCompileHandler(File javaFile) {
        if (javaFile == null) {
            throw new IllegalStateException("ClassFile is null!");
        }
        // 创建编译器对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // compiler 编译临时文件 javaFile，并将结果保存到同一目录下的 Main.class 文件中
        int result;
        // 不同版本Java兼容
        if (System.getProperty("java.version").startsWith("1.")) {
            result = compiler.run(null, null, null, javaFile.getPath());
        } else {
            result = compiler.run(null, null, null, "--release", "17", javaFile.getPath());
        }
        if (result != 0) {
            throw new IllegalStateException("Compilation Error!");
        }
        boolean delete = Objects.requireNonNull(javaFile).delete();
        if (!delete) {
            throw new IllegalStateException("JavaFile Delete Error!");
        }
        return javaFile;
    }
}
