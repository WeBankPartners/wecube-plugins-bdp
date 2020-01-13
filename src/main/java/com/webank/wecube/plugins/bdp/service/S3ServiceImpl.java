package com.webank.wecube.plugins.bdp.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.dto.S3InfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author howechen
 */
@Service
public class S3ServiceImpl implements S3Service {

    private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);
    private AmazonS3 s3Client;

    @Autowired
    public S3ServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public InputStream downloadObject(S3InfoDto s3InfoDto) throws BdpException {
        S3Object targetObject;
        InputStream resultInputStream;
        try {
            String msg = String.format("Downloading the given key: [%s] of given bucket: [%s]", s3InfoDto.getKeyName(), s3InfoDto.getBucketName());
            logger.info(msg);

            targetObject = this.s3Client.getObject(new GetObjectRequest(s3InfoDto.getBucketName(), s3InfoDto.getKeyName()));
            resultInputStream = targetObject.getObjectContent().getDelegateStream();
        } catch (SdkClientException ex) {
            logger.error(String.format("Download error, the error message is: [%s]", ex.getMessage()));
            if (logger.isDebugEnabled()) {
                logger.debug(s3InfoDto.toString());
            }
            throw new BdpException(ex.getMessage());
        }
        try {
            targetObject.close();
        } catch (IOException e) {
            throw new BdpException("Cannot close S3 object.");
        }
        return resultInputStream;
    }
}
