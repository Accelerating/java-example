package com.xxzou.javaexample.swagger.common;

public class ApiResponse {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;


    private int code;
    private String msg;
    private Object data;

    public static ApiResponse ok(String msg, Object data){
        ApiResponse response = new ApiResponse();
        response.setCode(SUCCESS);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static ApiResponse ok(Object data){
        return ok("ok", data);
    }

    public static ApiResponse error(String msg){
        ApiResponse response = new ApiResponse();
        response.setCode(ERROR);
        response.setMsg(msg);
        return response;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
