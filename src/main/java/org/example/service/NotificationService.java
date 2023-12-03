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

import java.time.LocalDate;
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
        // Retrieve all notifications from the repository
        List<NotificationEntity> notifications = notificationRepository.findAll();
        // Map each NotificationEntity to a NotificationDTO using the mapper, then collect into a list
        return notifications.stream()
                .map(notificationMapper::mapNotificationEntityToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO, Long taskId) {
        try {
            // Try to find the associated task by ID using TaskService
            TaskSearchDTO taskEntity = taskService.findTaskById(taskId)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found"));

            // Create a new NotificationEntity with data from the DTO and associated task
            NotificationEntity notificationEntity = NotificationEntity.builder()
                    .message(notificationDTO.getMessage())
                    .timestamp(LocalDateTime.now())
                    .task(taskMapper.mapTaskSearchDTOToEntity(taskEntity))
                    .build();
            // Save the new notification to the repository
            NotificationEntity createdNotificationEntity = notificationRepository.save(notificationEntity);
            return notificationMapper.mapNotificationEntityToDTO(createdNotificationEntity);
        } catch (EntityNotFoundException ex) {
            // If the associated task is not found, throw a Not Found exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found", ex);
        }
    }

    public void deleteAllNotifications() {
        // Call the repository to delete all notifications
        notificationRepository.deleteAll();
    }
}


