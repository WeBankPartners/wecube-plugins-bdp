package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.BaseSpringBootTest;
import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import com.webank.wecube.plugins.bdp.dto.S3InfoDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class S3ServiceTest extends BaseSpringBootTest {

    @Autowired
    private S3ServiceImpl s3Service;
    @Autowired
    private ApplicationProperties applicationProperties;


    @Test
    public void givenS3InfoDto_downloadFile_shouldSucceed() {
        S3InfoDto s3InfoDto = this.mockS3InfoDto();
        s3Service.downloadObject(s3InfoDto);
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
}
