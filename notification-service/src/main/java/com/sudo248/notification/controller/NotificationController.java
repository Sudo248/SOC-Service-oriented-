package com.sudo248.notification.controller;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import com.sudo248.notification.repository.entity.Notification;
import com.sudo248.notification.repository.entity.User;
import com.sudo248.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/token/{token}")
    public ResponseEntity<BaseResponse<?>> postToken(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("token") String token
    ) {
        return Utils.handleException(() -> {
            User user = new User(userId, token);
            return BaseResponse.ok(notificationService.saveUser(user));
        });
    }

    @PostMapping("/send/topic/{topic}")
    public ResponseEntity<BaseResponse<?>> sendNotificationToTopic(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("topic") String topic,
            @RequestBody Notification notification
    ) {
        return Utils.handleException(() -> BaseResponse.ok(notificationService.sendNotificationToTopic(userId, topic, notification)));
    }

    @PostMapping("/send/promotion")
    public ResponseEntity<BaseResponse<?>> sendNotificationToTopic(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody Notification notification
    ) {
        return Utils.handleException(() -> BaseResponse.ok(notificationService.sendNotificationToTopic(userId, NotificationService.promotionTopic, notification)));
    }
}
