package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.dtos.TaskCreateDTO;

import org.example.model.dtos.TaskSearchDTO;
import org.example.model.dtos.TaskUpdateDTO;
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

    // Constructor to inject dependencies
    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Optional<TaskSearchDTO> findTaskById(Long id) {
        return taskRepository.findById(id).map(taskMapper::mapTaskEntityToUserSearchDTO);
    }

    public List<TaskSearchDTO> findAllTasks() {
        // Retrieve all task entities from the repository
        List<TaskEntity> taskEntities = taskRepository.findAll();
        // Map each TaskEntity to a TaskSearchDTO using the mapper, then collect into a list
        return taskEntities.stream()
                .map(taskMapper::mapTaskEntityToUserSearchDTO)
                .collect(Collectors.toList());
    }
    public void deleteTaskById(Long id){
        // Call the repository to delete the task by ID
        taskRepository.deleteById(id);
    }

    public TaskCreateDTO createTask(TaskCreateDTO taskDTO){
        // Map the TaskCreateDTO to a TaskEntity
        TaskEntity taskEntity = taskMapper.mapTaskDTOtoTaskEntity(taskDTO);
        // Save the new task to the repository
        TaskEntity createdTaskEntity = taskRepository.save(taskEntity);
        // Map the created TaskEntity to a TaskCreateDTO and return
        return taskMapper.mapTaskEntityToTaskDTO(createdTaskEntity);
    }

    public TaskUpdateDTO updateTask(Long id, TaskUpdateDTO taskUpdateDTO) {
        // Find the existing task by ID or throw an exception if not found
        TaskEntity existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        // Update fields of the existing task with data from the TaskUpdateDTO
        existingTask.setName(taskUpdateDTO.getName());
        existingTask.setDescription(taskUpdateDTO.getDescription());
        existingTask.setDueDate(taskUpdateDTO.getDueDate());
        existingTask.setPriority(taskUpdateDTO.getPriority());
        existingTask.setStatus(taskUpdateDTO.getStatus());

        // Save the updated task to the repository
        TaskEntity updatedTaskEntity = taskRepository.save(existingTask);
        // Map the updated TaskEntity to a TaskUpdateDTO and return
        return taskMapper.mapTaskEntityToUpdateDTO(updatedTaskEntity);
    }
}


