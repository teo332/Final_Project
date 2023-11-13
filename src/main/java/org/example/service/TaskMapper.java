package org.example.service;

import org.example.model.dtos.TaskCreateDTO;

import org.example.model.dtos.TaskSearchDTO;
import org.example.model.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskEntity mapTaskDTOtoTaskEntity(TaskCreateDTO taskCreateDTO){
        TaskEntity task = new TaskEntity();
        task.setDescription(taskCreateDTO.getDescription());
        task.setName(taskCreateDTO.getName());
        task.setStatus(taskCreateDTO.getStatus());
        task.setPriority(taskCreateDTO.getPriority());
        task.setDueDate(taskCreateDTO.getDueDate());
        return task;
    }
    public TaskCreateDTO mapTaskEntityToTaskDTO(TaskEntity task){
        return TaskCreateDTO.builder().name(task.getName()).description(task.getDescription())
                .priority(task.getPriority()).status(task.getStatus()).dueDate(task.getDueDate()).build();

    }
    public TaskSearchDTO mapTaskEntityToUserSearchDTO(TaskEntity task){
        return TaskSearchDTO.builder().id(task.getId()).name(task.getName()).dueDate(task.getDueDate())
                .priority(task.getPriority()).status(task.getStatus()).description(task.getDescription()).build();
    }
}
