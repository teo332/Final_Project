package org.example.repository;

import org.example.model.dtos.TaskDTO;
import org.example.model.entities.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TaskRepository {

    private TaskDTO taskDTO1 = new TaskDTO(01, "Fix car", "Is leaking oil",
            "tomorrow", "high", "incomplete");
    private TaskDTO taskDTO2 = new TaskDTO(02, "Get groceries", "Buy what's on th list",
            "15.11.2023", "low", "incomplete");
    private TaskDTO taskDTO3 = new TaskDTO(03, "Homework", "Start the project",
            "01.12.2023", "high", "complete");

    private List<TaskEntity> taskEntities = new ArrayList<>();
    public List<TaskDTO> findTaskById(int id) {
        List<TaskDTO> matchingTasks = Stream.of(taskDTO1, taskDTO2, taskDTO3)
                .filter(taskDTO -> taskDTO.getId() == id)
                .collect(Collectors.toList());

        return matchingTasks;
    }

    public TaskEntity createTask(TaskEntity taskToSaveInDB){
        taskEntities.add(taskToSaveInDB);
        return taskToSaveInDB;
    }

    public void deleteTaskById(int id) {
        taskDTO1.setId(id);
        taskDTO2.setId(id);
        taskDTO3.setId(id);

        taskEntities.removeIf(taskEntity -> taskEntity.getId() == id);
    }

    public List<TaskDTO> findAllTasks() {
        return Collections.unmodifiableList(List.of(taskDTO1, taskDTO2, taskDTO3));
    }








}
