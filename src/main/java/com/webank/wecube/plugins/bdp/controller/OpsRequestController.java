package com.webank.wecube.plugins.bdp.controller;

import com.webank.wecube.plugins.bdp.common.ApplicationConstants;
import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.dto.CommonRequestDto;
import com.webank.wecube.plugins.bdp.dto.CommonResponseDto;
import com.webank.wecube.plugins.bdp.service.OpsRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author howechen
 */
@RestController
@RequestMapping(ApplicationConstants.ApiInfo.URL_PREFIX)
public class OpsRequestController {

    private OpsRequestServiceImpl opsRequestService;

    @Autowired
    public OpsRequestController(OpsRequestServiceImpl opsRequestService) {
        this.opsRequestService = opsRequestService;
    }

    @PostMapping("/record")
    @ResponseBody
    public CommonResponseDto addRecord(@RequestBody CommonRequestDto commonRequestDto) {
        try {
            this.opsRequestService.addRecord(commonRequestDto.getInputs());
        } catch (BdpException ex) {
            return CommonResponseDto.error(ex.getMessage());
        }
        return CommonResponseDto.okay();
    }

}
