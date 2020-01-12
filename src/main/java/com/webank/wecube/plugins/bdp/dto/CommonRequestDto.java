package com.webank.wecube.plugins.bdp.dto;

import java.util.List;

/**
 * @author howechen
 */
public class CommonRequestDto {

    /**
     * requestId : request-001
     * operator : admin
     * inputs : [{"callbackParameter":"callback001","taskName":"task-001","roleName":"admin","callbackUrl":"/v1/process/instances/callback"},{"callbackParameter":"callback002","taskName":"task-002","roleName":"admin","callbackUrl":"/v1/process/instances/callback"}]
     */

    private String requestId;
    private String operator;
    private List<ItsmRequestDto> inputs;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<ItsmRequestDto> getInputs() {
        return inputs;
    }

    public void setInputs(List<ItsmRequestDto> inputs) {
        this.inputs = inputs;
    }
}
