package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.BaseSpringBootTest;
import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import com.webank.wecube.plugins.bdp.dto.ItsmRequestDto;
import com.webank.wecube.plugins.bdp.dto.S3InfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class OpsRequestServiceTest extends BaseSpringBootTest {


    @Autowired
    private OpsRequestServiceImpl opsRequestService;
    @Autowired
    private ApplicationProperties applicationProperties;


    @Test
    public void givenS3Info_downloadAndTransferToDataFrameList_shouldSucceed() {
        ItsmRequestDto itsmRequestDto = mockItsmRequestDto();
        this.opsRequestService.addRecord(Collections.singletonList(itsmRequestDto));
    }

    private S3InfoDto mockS3InfoDto() {
        return new S3InfoDto(
                applicationProperties.getHost(),
                applicationProperties.getPort(),
                applicationProperties.getUsername(),
                applicationProperties.getPassword(),
                "service-mgmt",
                "表单示例-用户服务.xlsx");
    }

    private ItsmRequestDto mockItsmRequestDto() {
        return new ItsmRequestDto(
                "",
                "",
                "",
                mockS3InfoDto(),
                "",
                "",
                "",
                "",
                ""
        );
    }


}
