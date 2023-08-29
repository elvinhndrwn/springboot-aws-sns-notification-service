package com.example.springbootawsnotification.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:265727831911:notification-email";
    private static final String TOPIC_PROTOCOL = "email";

    // Send add subscription email
    @GetMapping("/add-subscription/{email}")
    public String addSubscription(@PathVariable String email){
        try {
            SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, TOPIC_PROTOCOL, email);
            amazonSNSClient.subscribe(request);
            return "Subscription Request successfully sent to: " + email;
        }catch (Exception e){
            log.error("error message: {}", e.getMessage());
            return "Send subscription Request is failed";
        }
    }

    // Publish Message
    @GetMapping("/send-notification")
    public String sendNotification(){
        try {
            PublishRequest request = new PublishRequest(TOPIC_ARN, "Hello world!", "Notification Test");
            amazonSNSClient.publish(request);
            return "Successfully send notification";
        }catch (Exception e){
            log.error("error message: {}", e.getMessage());
            return "Failed send notification";
        }
    }
}
