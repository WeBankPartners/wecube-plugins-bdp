package com.webank.wecube.plugins.bdp.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.webank.wecube.plugins.bdp.common.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author howechen
 */
@Configuration
public class S3ClientConfig {

    private Logger logger = LoggerFactory.getLogger(S3ClientConfig.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public AmazonS3 s3Client() {
        this.logger.info("Creating S3 client");
        String endPoint = String.format("http://%s:%s", this.applicationProperties.getHost(), this.applicationProperties.getPort());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, Regions.DEFAULT_REGION.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.applicationProperties.getUsername(), this.applicationProperties.getPassword())))
                .build();
    }
}
