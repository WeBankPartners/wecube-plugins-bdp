package com.webank.wecube.plugins.bdp.dto;

public class OpsResponseDto {
    public final static int STATUS_OK = 0;
    private int code;
    private Object data;
    private String message;

    public OpsResponseDto(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public OpsResponseDto() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
