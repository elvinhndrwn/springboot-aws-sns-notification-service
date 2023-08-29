package com.example.springbootawsnotification.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSConfig {

    private static final String AWS_ACCESS_KEY = "AKIAT3XU455TQG6DX4GM";
    private static final String AWS_SECRET_KEY = "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";

    @Primary
    @Bean
    public AmazonSNSClient getSNSClient(){
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)))
                .build();
    }
}
