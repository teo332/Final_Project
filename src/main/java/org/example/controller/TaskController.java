package org.example.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.example.model.CustomResponseDTO;
import org.example.model.dtos.TaskCreateDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.model.dtos.TaskUpdateDTO;
import org.example.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
public class TaskController {
     List <TaskCreateDTO> taskDTOList = new ArrayList<>();

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<TaskSearchDTO>> findTaskById(@PathVariable(required = false) Long id){
        return ResponseEntity.ok(taskService.findTaskById(id));
    }
    @GetMapping("/showAll")
    public ResponseEntity<List<TaskSearchDTO>> findAllTasks(){
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponseDTO> createNewTask(@RequestBody @Valid TaskCreateDTO taskDTO, BindingResult bindingResult){
        //Create a response object to be sent back
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        //Check for validation errors
        if (bindingResult != null && bindingResult.hasErrors()) {
            //If there are validation errors, set a message and return Bad Request
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        //Call the TaskService to create a new task
        taskService.createTask(taskDTO);
        // Set a success response and return CREATED status
        customResponseDTO.setResponseObject(taskDTO);
        taskDTOList.add(taskDTO);
        customResponseDTO.setResponseMessage("Task created successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
        //Call the TaskService to delete a task by ID
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponseDTO> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskUpdateDTO taskUpdateDTO,
            BindingResult bindingResult
    ) {
        //Create a response object to be sent back
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        //Check for validation errors
        if (bindingResult != null && bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        try {
            //Try to update the task using TaskService
            TaskUpdateDTO updatedTask = taskService.updateTask(id, taskUpdateDTO);

            //If successful, set a success response and return OK
            customResponseDTO.setResponseObject(updatedTask);
            customResponseDTO.setResponseMessage("Task updated successfully");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {

            //If the task is not found, throw a Not Found exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found", ex);
        }
    }
}




