package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.dtos.NotificationDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.model.entities.NotificationEntity;
import org.example.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final TaskService taskService;
    private final NotificationMapper notificationMapper;
    private final TaskMapper taskMapper;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, TaskService taskService, NotificationMapper notificationMapper, TaskMapper taskMapper) {
        this.notificationRepository = notificationRepository;
        this.taskService = taskService;
        this.notificationMapper = notificationMapper;
        this.taskMapper = taskMapper;
    }

    public List<NotificationDTO> getAllNotifications() {
        List<NotificationEntity> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notificationMapper::mapNotificationEntityToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO, Long taskId) {
        try {
            TaskSearchDTO taskEntity = taskService.findTaskById(taskId)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found"));

            NotificationEntity notificationEntity = NotificationEntity.builder()
                    .message(notificationDTO.getMessage())
                    .timestamp(LocalDateTime.now())
                    .task(taskMapper.mapTaskSearchDTOToEntity(taskEntity))
                    .build();

            NotificationEntity createdNotificationEntity = notificationRepository.save(notificationEntity);
            return notificationMapper.mapNotificationEntityToDTO(createdNotificationEntity);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found", ex);
        }
    }

    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }
}


