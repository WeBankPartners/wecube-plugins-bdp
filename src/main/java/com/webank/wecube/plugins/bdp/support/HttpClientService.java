package com.webank.wecube.plugins.bdp.support;

import com.webank.wecube.plugins.bdp.dto.OpsResponseDto;
import com.webank.wecube.plugins.bdp.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientService {
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    public HttpClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpsResponseDto initPostRequest(String url, Object data) {
        ResponseEntity<String> responseFromRequest = RestTemplateUtils.sendPostRequestWithBody(this.restTemplate, url, this.httpHeaders, data);
        return RestTemplateUtils.checkResponse(responseFromRequest);
    }

}
