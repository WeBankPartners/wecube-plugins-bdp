package com.webank.wecube.plugins.bdp.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import com.webank.wecube.plugins.bdp.common.BdpException;
import com.webank.wecube.plugins.bdp.support.s3.S3Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author howechen
 */
@Service
public class S3ServiceImpl implements S3Service {

    private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);
    private ApplicationProperties.ItsmS3Properties itsmS3Properties;

    @Autowired
    public S3ServiceImpl(ApplicationProperties.ItsmS3Properties itsmS3Properties) {
        this.itsmS3Properties = itsmS3Properties;
    }

    @Override
    public InputStream downloadObject(String fileUrl) throws BdpException {
        S3Object targetObject;
        InputStream resultInputStream;
        S3Info s3Info;
        try {
            s3Info = this.handleS3Url(fileUrl);
            AmazonS3 s3Client = this.initS3Client(s3Info.getHost(), s3Info.getPort());

            String msg = String.format("Downloading the given key: [%s] of given bucket: [%s]", s3Info.getKey(), s3Info.getBucket());
            logger.info(msg);

            targetObject = s3Client.getObject(new GetObjectRequest(s3Info.getBucket(), s3Info.getKey()));
            resultInputStream = targetObject.getObjectContent().getDelegateStream();
        } catch (SdkClientException ex) {
            logger.error(String.format("Download error, the error message is: [%s]", ex.getMessage()));
            if (logger.isDebugEnabled()) {
                String msg = String.format("The fileUrl is: [%s]", fileUrl);
                logger.debug(msg);
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

    private S3Info handleS3Url(String fileUrl) throws BdpException {
        final int splitPathSize = 3;
        final int bucketIndex = 1;
        final int keyIndex = 2;

        URL s3Url = null;
        try {
            logger.info(String.format("Handling s3 url: [%s]", fileUrl));
            s3Url = new URL(fileUrl);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        List<String> splitPath = Arrays.asList(Objects.requireNonNull(s3Url, "The s3Url cannot be null.").getPath().split("/"));
        if (splitPath.size() != splitPathSize) {
            throw new BdpException("The file url path should only contains bucket and key names.");
        }

        String host = s3Url.getHost();
        String port = String.valueOf(s3Url.getPort());
        String bucket = splitPath.get(bucketIndex);
        String key = splitPath.get(keyIndex);
        return new S3Info(host, port, bucket, key);
    }

    private AmazonS3 initS3Client(String host, String port) {
        this.logger.info("Creating S3 client");
        String endPoint = String.format("http://%s:%s", host, port);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, Regions.DEFAULT_REGION.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.itsmS3Properties.getUsername(), this.itsmS3Properties.getPassword())))
                .build();
    }
}
