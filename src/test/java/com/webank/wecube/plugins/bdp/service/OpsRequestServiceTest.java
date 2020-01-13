package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.BaseSpringBootTest;
import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class OpsRequestServiceTest extends BaseSpringBootTest {


    @Autowired
    private OpsRequestServiceImpl opsRequestService;
    @Autowired
    private ApplicationProperties applicationProperties;


}
