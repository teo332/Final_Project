package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.dtos.TaskCreateDTO;

import org.example.model.dtos.TaskSearchDTO;
import org.example.model.entities.TaskEntity;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Optional<TaskSearchDTO> findTaskById(Long id) {
        return taskRepository.findById(id).map(taskMapper::mapTaskEntityToUserSearchDTO);
    }

    public List<TaskSearchDTO> findAllTasks() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities.stream()
                .map(taskMapper::mapTaskEntityToUserSearchDTO)
                .collect(Collectors.toList());
    }
    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }

    public TaskCreateDTO createTask(TaskCreateDTO taskDTO){
        TaskEntity taskEntity = taskMapper.mapTaskDTOtoTaskEntity(taskDTO);
        TaskEntity createdTaskEntity = taskRepository.save(taskEntity);
        return taskMapper.mapTaskEntityToTaskDTO(createdTaskEntity);
    }

}
