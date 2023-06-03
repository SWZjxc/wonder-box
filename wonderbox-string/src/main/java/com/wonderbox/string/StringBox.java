package com.wonderbox.string;

import com.wonderbox.exception.ErrorCode;
import com.wonderbox.exception.ExceptionBox;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Ge Mingjia
 * @date 2023/6/1
 */
public class StringBox {


    /**
     * 去除字符串中多余的空格和换行符
     *
     * @param originString
     * @return
     */
    public static String inlineString(String originString) {
        if (originString == null) {
            throw new ExceptionBox(ErrorCode.PARAMS_ERROR, "The parameter is not allowed to be null");
        }
        // 去掉换行符
        String newString = originString.replaceAll("\\n", " ");
        // 去掉连续的空格
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(newString);
        newString = matcher.replaceAll(" ");
        return newString;
    }

    /**
     * 获取代码文本中的导入语句 (Java)
     *
     * @param code
     * @return
     */
    public static String getImportCode(String code) {
        if (code == null) {
            throw new ExceptionBox(ErrorCode.PARAMS_ERROR, "The parameter is not allowed to be null");
        }
        Pattern pattern = Pattern.compile("import\\s+[^;]+;");
        Matcher matcher = pattern.matcher(code);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group());
        }
        return builder.toString().trim();
    }

    /**
     * 把数组转为[1 2 3]的形式
     *
     * @param result
     * @return
     */
    public static String splitArray(int[] result) {
        if (result == null || result.length < 1) {
            throw new ExceptionBox(ErrorCode.PARAMS_ERROR, "The parameter is not allowed to be null or empty");
        }
        return "[" + Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")) + "]";
    }

    /**
     * 去除字符串 originStr 中包含字符串 rmStr 的部分
     *
     * @param originStr
     * @param rmStr
     * @return
     */
    public static String removeSubstring(String originStr, String rmStr) {
        if (originStr == null || rmStr == null || originStr.length() == 0 || rmStr.length() == 0) {
            throw new ExceptionBox(ErrorCode.PARAMS_ERROR, "The parameter is not allowed to be null or empty");
        }
        return originStr.replace(rmStr, "");
    }

}
