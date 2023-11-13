package org.example.service;

import org.example.model.dtos.NotificationDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.model.entities.NotificationEntity;
import org.example.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final TaskService taskService;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, TaskService taskService, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.taskService = taskService;
        this.notificationMapper = notificationMapper;
    }

    public List<NotificationDTO> getAllNotifications() {
        List<NotificationEntity> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notificationMapper::mapNotificationEntityToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        TaskSearchDTO taskEntity = taskService.findTaskById(notificationDTO.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        NotificationEntity notificationEntity = NotificationEntity.builder()
                .message(notificationDTO.getMessage())
                .timestamp(LocalDate.from(LocalDateTime.now()))
                .build();

        NotificationEntity createdNotificationEntity = notificationRepository.save(notificationEntity);
        return notificationMapper.mapNotificationEntityToDTO(createdNotificationEntity);
    }
}


