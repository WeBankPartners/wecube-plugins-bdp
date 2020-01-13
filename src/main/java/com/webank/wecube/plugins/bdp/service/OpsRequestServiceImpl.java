package com.webank.wecube.plugins.bdp.service;

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
import java.util.ArrayList;
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

    @Autowired
    public OpsRequestServiceImpl(S3ServiceImpl s3Service, HttpClientService httpClientService) {
        this.s3Service = s3Service;
        this.httpClientService = httpClientService;
    }

    @Override
    public List<OpsResponseDto> addRecord(List<ItsmRequestDto> itsmRequestDtoList) {

        List<OpsResponseDto> resultList = new ArrayList<>();
        for (ItsmRequestDto itsmRequestDto : itsmRequestDtoList) {
            InputStream inputStream = this.s3Service.downloadObject(itsmRequestDto.getS3());
            List<Map<String, String>> dataFrameList = ExcelUtils.excelToMap(inputStream);
            OpsRequestDto requestDto = wrapDataFrameToOpsRequestDto(itsmRequestDto, dataFrameList);
            resultList.add(this.httpClientService.initPostRequest(OpsRequestInfo.ADD_RECORD_URL, requestDto));
        }
        return resultList;
    }

    private OpsRequestDto wrapDataFrameToOpsRequestDto(ItsmRequestDto itsmRequestDto, List<Map<String, String>> dataFrameList) {
        logger.info("Wrapping up the data frame to the request JSON form of OPS");
        return new OpsRequestDto(
                itsmRequestDto.getHandler(),
                itsmRequestDto.getFormType(),
                JsonUtils.toBase64String(JsonUtils.toJsonString(dataFrameList)),
                itsmRequestDto.getCreateTime(),
                itsmRequestDto.getEnvType(),
                itsmRequestDto.getCreateUser(),
                itsmRequestDto.getRequestNo(),
                itsmRequestDto.getRecordSource());
    }
}
