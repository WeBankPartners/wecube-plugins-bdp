package com.webank.wecube.plugins.bdp.service;

import java.io.InputStream;

public interface S3Service {

    InputStream downloadObject(String fileUrl);
}
