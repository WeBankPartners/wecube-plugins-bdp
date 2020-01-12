package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.dto.S3InfoDto;

import java.io.InputStream;

public interface S3Service {

    InputStream downloadObject(S3InfoDto s3InfoDto);
}
