package org.example.service;

import org.example.model.dtos.NotificationDTO;
import org.example.model.entities.NotificationEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificationMapper {

    public NotificationEntity mapNotificationDTOtoEntity(NotificationDTO notificationDTO) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessage(notificationDTO.getMessage());

        return notificationEntity;
    }

    public NotificationDTO mapNotificationEntityToDTO(NotificationEntity notificationEntity) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notificationEntity.getId());
        notificationDTO.setMessage(notificationEntity.getMessage());
        notificationDTO.setTimestamp(LocalDate.from(notificationEntity.getTimestamp()));
        notificationDTO.setTaskId(notificationEntity.getTask() != null ? notificationEntity.getTask().getId() : null);

        return notificationDTO;
    }
}

