package com.wonderbox.json;

import com.wonderbox.exception.ErrorCode;
import com.wonderbox.exception.ExceptionBox;

/**
 * @author Ge Mingjia
 * @date 2023/6/3
 */
public class JsonBox {

    /**
     * 转义字符串为JSON可以传输的形式 " -> \\\"
     *
     * @param originString
     * @return
     */
    public static String escapeStringToJson(String originString) {
        if (originString == null) {
            throw new ExceptionBox(ErrorCode.PARAMS_ERROR, "The parameter is not allowed to be null");
        }
        return originString.replace("\"", "\\\"");
    }
}
