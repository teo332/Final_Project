package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.CustomResponseDTO;
import org.example.model.dtos.TaskCreateDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        taskService.createTask(taskDTO);
        customResponseDTO.setResponseObject(taskDTO);
        taskDTOList.add(taskDTO);
        customResponseDTO.setResponseMessage("Task created successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }


}

