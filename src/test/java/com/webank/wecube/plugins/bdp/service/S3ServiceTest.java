package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.BaseSpringBootTest;
import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class S3ServiceTest extends BaseSpringBootTest {

    @Autowired
    private S3ServiceImpl s3Service;
    @Autowired
    private ApplicationProperties applicationProperties;
}
