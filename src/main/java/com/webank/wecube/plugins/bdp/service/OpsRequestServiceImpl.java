package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.dto.ItsmRequestDto;
import com.webank.wecube.plugins.bdp.dto.OpsRequestDto;
import com.webank.wecube.plugins.bdp.dto.OpsResponseDto;
import com.webank.wecube.plugins.bdp.support.HttpClientService;
import com.webank.wecube.plugins.bdp.support.OpsRequestInfo;
import com.webank.wecube.plugins.bdp.utils.ExcelUtils;
import com.webank.wecube.plugins.bdp.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author howechen
 */
@Service
public class OpsRequestServiceImpl implements OpsRequestService {

    private static Logger logger = LoggerFactory.getLogger(OpsRequestServiceImpl.class);
    private S3ServiceImpl s3Service;
    private HttpClientService httpClientService;
    private ApplicationProperties.OpsProperties opsProperties;

    @Autowired
    public OpsRequestServiceImpl(S3ServiceImpl s3Service, HttpClientService httpClientService, ApplicationProperties.OpsProperties opsProperties) {
        this.s3Service = s3Service;
        this.httpClientService = httpClientService;
        this.opsProperties = opsProperties;
    }

    @Override
    public void addRecord(List<ItsmRequestDto> itsmRequestDtoList) throws BdpException {

        for (ItsmRequestDto itsmRequestDto : itsmRequestDtoList) {
            InputStream inputStream = this.s3Service.downloadObject(itsmRequestDto.getFileUrl());
            List<Map<String, String>> dataFrameList = ExcelUtils.excelToMap(inputStream);
            OpsRequestDto requestDto = wrapDataFrameToOpsRequestDto(itsmRequestDto, dataFrameList);
            OpsResponseDto responseDto = this.httpClientService.initPostRequest(opsProperties.getUrl() + OpsRequestInfo.ADD_RECORD_POSTFIX, requestDto);
            if (!isOpsOperationCorrect(responseDto)) {
                throw new BdpException(responseDto.getMessage());
            }
        }
    }

    private OpsRequestDto wrapDataFrameToOpsRequestDto(ItsmRequestDto itsmRequestDto, List<Map<String, String>> dataFrameList) {
        logger.info("Wrapping up the data frame to the request JSON form of OPS");
        return new OpsRequestDto(
                itsmRequestDto.getHandler(),
                JsonUtils.toBase64String(JsonUtils.toJsonString(dataFrameList)),
                itsmRequestDto.getCreateTime(),
                itsmRequestDto.getEnvType(),
                itsmRequestDto.getCreateUser(),
                itsmRequestDto.getRequestNo(),
                itsmRequestDto.getRecordSource());
    }

    private boolean isOpsOperationCorrect(OpsResponseDto opsResponseDto) {
        return OpsResponseDto.STATUS_OK == opsResponseDto.getCode();
    }
}
