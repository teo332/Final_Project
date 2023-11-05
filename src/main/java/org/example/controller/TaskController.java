package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.CustomResponseDTO;
import org.example.model.dtos.TaskDTO;
import org.example.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@Controller
public class TaskController {
     List <TaskDTO> taskDTOList = new ArrayList<>();

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> findTaskById(@RequestParam(required = false)int id){
        return ResponseEntity.ok(taskService.findTaskById(id));
    }
    @GetMapping("/showAll")
    public ResponseEntity<List<TaskDTO>> findAllTasks(){
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponseDTO> createNewTask(@RequestBody @Valid TaskDTO taskDTO, BindingResult bindingResult){
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
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable int id){
        taskService.deleteTaskById(id);
    }


}

