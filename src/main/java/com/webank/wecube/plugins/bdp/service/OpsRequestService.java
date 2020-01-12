package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.dto.ItsmRequestDto;
import com.webank.wecube.plugins.bdp.dto.OpsResponseDto;

import java.util.List;

/**
 * @author howechen
 */
public interface OpsRequestService {
    List<OpsResponseDto> addRecord(List<ItsmRequestDto> itsmRequestDtoList);
}
