package com.webank.wecube.plugins.bdp.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author howechen
 */
public class OpsRequestDto {
    private String handler;
    private String formType = "BDP_CREATE_USER";
    private String data;
    private String createTime;
    private String envType;
    private String createUser;
    private Long requestNo;
    private String recordSource = "WeCube";

    public OpsRequestDto(String handler, String data, String createTime, String envType, String createUser, Long requestNo) {
        this.handler = handler;
        this.data = data;
        this.createTime = createTime;
        this.envType = envType;
        this.createUser = createUser;
        this.requestNo = requestNo;
    }

    public OpsRequestDto() {
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public Long getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(Long requestNo) {
        this.requestNo = requestNo;
    }

    public String getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("handler", handler)
                .append("formType", formType)
                .append("data", data)
                .append("createTime", createTime)
                .append("envType", envType)
                .append("createUser", createUser)
                .append("requestNo", requestNo)
                .append("recordSource", recordSource)
                .toString();
    }
}
