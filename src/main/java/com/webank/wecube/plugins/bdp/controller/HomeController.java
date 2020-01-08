package com.webank.wecube.plugins.bdp.controller;

import com.webank.wecube.plugins.bdp.common.ApplicationConstants;
import com.webank.wecube.plugins.bdp.dto.CommonResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author howechen
 */
@RestController
@RequestMapping(ApplicationConstants.ApiInfo.URL_PREFIX)
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public CommonResponseDto hello() {
        return CommonResponseDto.okay().withData("Hello, world");
    }

}
