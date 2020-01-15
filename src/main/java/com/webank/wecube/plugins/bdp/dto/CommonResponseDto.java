package com.webank.wecube.plugins.bdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CommonResponseDto {
    public final static String STATUS_OK = "0";
    public final static String STATUS_ERROR = "1";

    @JsonProperty(value = "result_code")
    private String resultCode;
    @JsonProperty(value = "result_message")
    private String resultMessage;
    @JsonProperty(value = "results")
    private Results results;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(List<Object> opsResponseDtoList) {

        this.results = new Results(opsResponseDtoList);
    }

    public CommonResponseDto withData(List<Object> data) {
        setResults(data);
        return this;
    }

    public static CommonResponseDto okay() {
        CommonResponseDto result = new CommonResponseDto();
        result.setResultCode(STATUS_OK);
        result.setResultMessage("Success");
        return result;
    }

    public static CommonResponseDto okayWithData(List<Object> data) {
        return okay().withData(data);
    }

    public static CommonResponseDto error(String errorMessage) {
        CommonResponseDto result = new CommonResponseDto();
        result.setResultCode(STATUS_ERROR);
        result.setResultMessage(errorMessage);
        return result;
    }

    public static class Results {
        private List<Object> outputs;

        public Results(List<Object> outputs) {
            this.outputs = outputs;
        }

        public Results() {
        }

        public List<Object> getOutputs() {
            return outputs;
        }

        public void setOutputs(List<Object> outputs) {
            this.outputs = outputs;
        }
    }
}

