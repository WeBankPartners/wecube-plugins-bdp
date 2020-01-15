package com.webank.wecube.plugins.bdp.dto;

public class ItsmRequestDto {
    private String handler;
    private String fileUrl;
    private String createTime;
    private String envType;
    private String createUser;
    private String requestNo;

    public ItsmRequestDto(String handler, String fileUrl, String createTime, String envType, String createUser, String requestNo) {
        this.handler = handler;
        this.fileUrl = fileUrl;
        this.createTime = createTime;
        this.envType = envType;
        this.createUser = createUser;
        this.requestNo = requestNo;
    }

    public ItsmRequestDto() {
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEnvType() {
        return envType;
    }

    public void setEnvType(String envType) {
        this.envType = envType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
