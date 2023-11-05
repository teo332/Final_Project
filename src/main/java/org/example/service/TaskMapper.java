package org.example.service;

import org.example.model.dtos.TaskDTO;
import org.example.model.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskEntity mapTaskDTOtoTaskEntity(TaskDTO taskDTO){
        return new TaskEntity(taskDTO.getId(), taskDTO.getName(), taskDTO.getDescription(),
                taskDTO.getDueDate(),taskDTO.getPriority(),taskDTO.getStatus());
    }
    public TaskDTO mapTaskEntityToTaskDTO(TaskEntity taskEntity){
        return TaskDTO.builder().id(taskEntity.getId()).name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .dueDate(taskEntity.getDueDate()).priority(taskEntity.getPriority())
                .status(taskEntity.getStatus()).build();

    }
}
