package com.webank.wecube.plugins.bdp.utils;

import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.dto.OpsResponseDto;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class RestTemplateUtils {

    /**
     * Send get request to url with params
     *
     * @param restTemplate restTemplate
     * @param requestUri   target uri
     * @param headers      request headers
     * @return String
     */
    public static ResponseEntity<String> sendGetRequestWithUrlParamMap(RestTemplate restTemplate, String requestUri, HttpHeaders headers) {
        HttpMethod method = HttpMethod.GET;
        // set content type as form
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // setup http request entity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        // send request and exchange the response to target class
        return restTemplate.exchange(requestUri, method, requestEntity, String.class);
    }

    /**
     * Send get request to url with params
     *
     * @param restTemplate restTemplate
     * @param requestUri   target uri
     * @param headers      request headers
     * @return String
     */
    public static ResponseEntity<String> sendGetRequestWithUrlParamMap(RestTemplate restTemplate, URI requestUri, HttpHeaders headers) {
        return sendGetRequestWithUrlParamMap(restTemplate, requestUri.getPath(), headers);
    }

    /**
     * Send get request to url without params
     *
     * @param restTemplate restTemplate
     * @param url          target url
     * @return String
     */
    public ResponseEntity<String> sendGetRequestWithoutUrlParamMap(RestTemplate restTemplate, String url) {
        return restTemplate.getForEntity(url, String.class);
    }

    /**
     * Send post request to url with params
     *
     * @param restTemplate restTemplate
     * @param requestUri   target uri
     * @param headers      request headers
     * @return String
     */
    public static ResponseEntity<String> sendPostRequestWithBody(RestTemplate restTemplate, String requestUri, HttpHeaders headers, List<Map<String, Object>> requestParamMap) {

        HttpMethod method = HttpMethod.POST;
        // set content type as form
        headers.setContentType(MediaType.APPLICATION_JSON);
        // setup http request entity
        HttpEntity<List<Map<String, Object>>> requestEntity = new HttpEntity<>(requestParamMap, headers);

        // send request and exchange the response to target class
        return restTemplate.exchange(requestUri, method, requestEntity, String.class);
    }

    /**
     * Send post request to url with params
     *
     * @param restTemplate restTemplate
     * @param requestUri   target uri
     * @param headers      request headers
     * @return String
     */
    public static ResponseEntity<String> sendPostRequestWithBody(RestTemplate restTemplate, String requestUri, HttpHeaders headers, Object requestBody) {

        HttpMethod method = HttpMethod.POST;
        // set content type as form
        headers.setContentType(MediaType.APPLICATION_JSON);
        // setup http request entity
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

        // send request and exchange the response to target class
        return restTemplate.exchange(requestUri, method, requestEntity, String.class);
    }

    /**
     * Send post request to url with params
     *
     * @param restTemplate restTemplate
     * @param requestUri   target uri
     * @param headers      request headers
     * @return String
     */
    public static ResponseEntity<String> sendDeleteWithoutBody(RestTemplate restTemplate, String requestUri, HttpHeaders headers) {

        HttpMethod method = HttpMethod.DELETE;
        // set content type as form
        headers.setContentType(MediaType.APPLICATION_JSON);
        // setup http request entity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        // send request and exchange the response to target class
        return restTemplate.exchange(requestUri, method, requestEntity, String.class);
    }

    /**
     * Send post request to url with params
     *
     * @param restTemplate restTemplate
     * @param requestUri   target uri
     * @param headers      request headers
     * @return String
     */
    public static ResponseEntity<String> sendDeleteWithBody(RestTemplate restTemplate, String requestUri, HttpHeaders headers, Object requestBody) {

        HttpMethod method = HttpMethod.DELETE;
        // set content type as form
        headers.setContentType(MediaType.APPLICATION_JSON);
        // setup http request entity
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

        // send request and exchange the response to target class
        return restTemplate.exchange(requestUri, method, requestEntity, String.class);
    }

    /**
     * Check response from a http request
     *
     * @param response response from http request
     * @return transferred commonResponseDto from response
     * @throws BdpException while JsonUtils transferring response to CommonResponseDto class
     */
    public static OpsResponseDto checkResponse(ResponseEntity<String> response) throws BdpException {
        if (StringUtils.isEmpty(response.getBody()) || response.getStatusCode().isError()) {
            if (response.getStatusCode().is4xxClientError()) {
                throw new BdpException(String.format("The target server returned error code: [%s]. The target server doesn't implement the request controller.", response.getStatusCode().toString()));
            }

            if (response.getStatusCode().is5xxServerError()) {
                throw new BdpException(String.format("The target server returned error code: [%s], which is an target server's internal error.", response.getStatusCode().toString()));
            }
        }
        OpsResponseDto opsResponseDto;
        try {
            opsResponseDto = JsonUtils.toObject(response.getBody(), OpsResponseDto.class);
        } catch (IOException e) {
            String msg = "Cannot transfer response from target server to CommonResponseDto class, the target server doesn't standardize the response style.";
            throw new BdpException(msg, e);
        }

        if (OpsResponseDto.STATUS_OK != opsResponseDto.getCode()) {
            String msg = String.format("Request error! The error message is [%s]", opsResponseDto.getMessage());
            throw new BdpException(msg);
        }
        return opsResponseDto;
    }
}
