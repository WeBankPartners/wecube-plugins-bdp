package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.dto.ItsmRequestDto;

import java.util.List;

/**
 * @author howechen
 */
public interface OpsRequestService {
    void addRecord(List<ItsmRequestDto> itsmRequestDtoList);
}
