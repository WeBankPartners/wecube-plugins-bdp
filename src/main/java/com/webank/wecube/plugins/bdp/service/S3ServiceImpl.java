package com.webank.wecube.plugins.bdp.service;

import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.support.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author howechen
 */
@Service
public class S3ServiceImpl implements S3Service {

    private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);
    private ApplicationProperties.ItsmS3Properties itsmS3Properties;
    private HttpClientService httpClientService;

    @Autowired
    public S3ServiceImpl(ApplicationProperties.ItsmS3Properties itsmS3Properties, HttpClientService httpClientService) {
        this.itsmS3Properties = itsmS3Properties;
        this.httpClientService = httpClientService;
    }

    @Override
    public InputStream downloadObject(String fileUrl) throws BdpException {
        InputStream resultInputStream;
        try {
            String msg = String.format("Downloading the file from: [%s]", fileUrl);
            logger.info(msg);
            ResponseEntity<Resource> resourceResponseEntity = this.httpClientService.initDownloadRequest(fileUrl);
            resultInputStream = Objects.requireNonNull(resourceResponseEntity.getBody()).getInputStream();
        } catch (IOException ex) {
            logger.error(String.format("Download error, the error message is: [%s]", ex.getMessage()));
            if (logger.isDebugEnabled()) {
                String msg = String.format("The fileUrl is: [%s]", fileUrl);
                logger.debug(msg);
            }
            throw new BdpException(ex.getMessage());
        }
        return resultInputStream;
    }
}
