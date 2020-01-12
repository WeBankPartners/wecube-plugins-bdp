package com.webank.wecube.plugins.bdp.controller;

import com.webank.wecube.plugins.bdp.common.ApplicationConstants;
import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.dto.CommonRequestDto;
import com.webank.wecube.plugins.bdp.dto.CommonResponseDto;
import com.webank.wecube.plugins.bdp.dto.OpsResponseDto;
import com.webank.wecube.plugins.bdp.service.OpsRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author howechen
 */
@RestController
@RequestMapping(ApplicationConstants.ApiInfo.URL_PREFIX)
public class OpsRequestController {

    @Autowired
    private OpsRequestServiceImpl opsRequestService;

    @PostMapping("/record")
    @ResponseBody
    public CommonResponseDto addRecord(@RequestBody CommonRequestDto commonRequestDto) {
        List<OpsResponseDto> result;
        try {
            result = this.opsRequestService.addRecord(commonRequestDto.getInputs());
        } catch (BdpException ex) {
            return CommonResponseDto.error(ex.getMessage());
        }
        return CommonResponseDto.okayWithData(Collections.singletonList(result));
    }

}
