package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.dto.ItsmRequestDto;
import com.webank.wecube.plugins.bdp.dto.OpsResponseDto;
import com.webank.wecube.plugins.bdp.utils.ExcelUtils;
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

    @Autowired
    private S3ServiceImpl s3Service;


    @Override
    public List<OpsResponseDto> addRecord(List<ItsmRequestDto> itsmRequestDtoList) {

        List<OpsResponseDto> resultList = new ArrayList<>();
        for (ItsmRequestDto itsmRequestDto : itsmRequestDtoList) {
            InputStream inputStream = this.s3Service.downloadObject(itsmRequestDto.getS3());
            List<Map<String, String>> dataFrameList = ExcelUtils.excelToMap(inputStream);
        }

        return resultList;
    }
}
