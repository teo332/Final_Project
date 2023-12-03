package org.example.service;

import org.example.model.dtos.NotificationDTO;
import org.example.model.entities.NotificationEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public NotificationEntity mapNotificationDTOtoEntity(NotificationDTO notificationDTO) {
        // Create a new NotificationEntity
        NotificationEntity notificationEntity = new NotificationEntity();
        // Set the message of the entity from the corresponding field in the DTO
        notificationEntity.setMessage(notificationDTO.getMessage());

        return notificationEntity;
    }

    public NotificationDTO mapNotificationEntityToDTO(NotificationEntity notificationEntity) {
        NotificationDTO notificationDTO = new NotificationDTO();
        // Map fields from the entity to the corresponding fields in the DTO
        notificationDTO.setId(notificationEntity.getId());
        notificationDTO.setMessage(notificationEntity.getMessage());
        notificationDTO.setTimestamp(LocalDateTime.from(notificationEntity.getTimestamp()));
        // Set the taskId in the DTO only if the task in the entity is not null
        notificationDTO.setTaskId(notificationEntity.getTask() != null ? notificationEntity.getTask().getId() : null);

        return notificationDTO;
    }
}

