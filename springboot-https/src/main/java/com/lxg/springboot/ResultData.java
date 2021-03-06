package com.lxg.springboot;

public class ResultData {
    private String msg;
    
    private int code;
    
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData [msg=" + msg + ", code=" + code + ", data=" + data + "]";
    }
    
}
