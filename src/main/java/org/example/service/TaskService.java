package org.example.service;

import org.example.model.dtos.TaskDTO;
import org.example.model.entities.TaskEntity;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

    public List<TaskDTO> findTaskById(int id) {
        return taskRepository.findTaskById(id);
    }
    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAllTasks();
    }
    public void deleteTaskById(int id){
        taskRepository.deleteTaskById(id);
    }

    public TaskDTO createTask(TaskDTO taskDTO){
        TaskEntity taskEntity = taskMapper.mapTaskDTOtoTaskEntity(taskDTO);
        TaskEntity createdTaskEntity = taskRepository.createTask(taskEntity);
        return taskMapper.mapTaskEntityToTaskDTO(createdTaskEntity);
    }

}
