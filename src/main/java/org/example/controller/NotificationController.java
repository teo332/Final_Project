package org.example.controller;


import org.example.model.dtos.NotificationDTO;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/create/{taskId}")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO, @PathVariable Long taskId) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO, taskId);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllNotifications() {
        notificationService.deleteAllNotifications();
        return ResponseEntity.ok("All notifications deleted successfully");
    }


}
