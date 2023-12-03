package org.example.service;

import org.example.model.dtos.TaskCreateDTO;

import org.example.model.dtos.TaskSearchDTO;
import org.example.model.dtos.TaskUpdateDTO;
import org.example.model.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskEntity mapTaskDTOtoTaskEntity(TaskCreateDTO taskCreateDTO){
        // Create a new TaskEntity
        TaskEntity task = new TaskEntity();
        // Set fields in the entity from the corresponding fields in the DTO
        task.setDescription(taskCreateDTO.getDescription());
        task.setName(taskCreateDTO.getName());
        task.setStatus(taskCreateDTO.getStatus());
        task.setPriority(taskCreateDTO.getPriority());
        task.setDueDate(taskCreateDTO.getDueDate());
        return task;
    }
    public TaskCreateDTO mapTaskEntityToTaskDTO(TaskEntity task){
        // Create a new TaskCreateDTO using a builder and set fields from the entity
        return TaskCreateDTO.builder()
                .name(task.getName())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .build();
    }
    public TaskSearchDTO mapTaskEntityToUserSearchDTO(TaskEntity task){
        // Create a new TaskSearchDTO using a builder and set fields from the entity
        return TaskSearchDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .status(task.getStatus())
                .description(task.getDescription())
                .build();
    }

    // Method to map a TaskSearchDTO to a TaskEntity (only setting the ID)
    public TaskEntity mapTaskSearchDTOToEntity(TaskSearchDTO taskSearchDTO) {
        // Create a new TaskEntity and set the ID from the DTO
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskSearchDTO.getId());
        return taskEntity;
    }
    public TaskUpdateDTO mapTaskEntityToUpdateDTO(TaskEntity taskEntity) {
        // Create a new TaskUpdateDTO using a builder and set fields from the entity
        return TaskUpdateDTO.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .dueDate(taskEntity.getDueDate())
                .priority(taskEntity.getPriority())
                .status(taskEntity.getStatus())
                .build();
    }
}


