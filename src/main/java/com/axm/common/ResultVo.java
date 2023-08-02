package com.axm.common;

/**
 * @Author: AceXiamo
 * @ClassName: ResultVo
 * @Date: 2023/8/2 15:13
 */
public class ResultVo<T> {

    private int code;
    private String message;
    private T data;

    public ResultVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ResultVo<T> success() {
        return new ResultVo<>(200, "Success", null);
    }

    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(200, "Success", data);
    }

    public static <T> ResultVo<T> error(int code, String message) {
        return new ResultVo<>(code, message, null);
    }

}
