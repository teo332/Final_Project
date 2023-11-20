
import org.example.controller.TaskController;
import org.example.model.CustomResponseDTO;
import org.example.model.dtos.TaskCreateDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    public TaskControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findTaskByIdTest() {

        Long taskId = 1L;
        TaskSearchDTO mockTaskSearchDTO = new TaskSearchDTO();
        when(taskService.findTaskById(taskId)).thenReturn(java.util.Optional.of(mockTaskSearchDTO));

        ResponseEntity<Optional<TaskSearchDTO>> result = taskController.findTaskById(taskId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().isPresent());
    }

    @Test
    void findAllTasksTest() {

        List<TaskSearchDTO> mockTaskSearchDTOs = Collections.singletonList(new TaskSearchDTO());
        when(taskService.findAllTasks()).thenReturn(mockTaskSearchDTOs);

        ResponseEntity<List<TaskSearchDTO>> result = taskController.findAllTasks();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void createNewTaskTest() {

        TaskCreateDTO inputDTO = new TaskCreateDTO();
        inputDTO.setName("Test Task");

        when(taskService.createTask(inputDTO)).thenReturn(inputDTO);

        ResponseEntity<CustomResponseDTO> result = taskController.createNewTask(inputDTO, null);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Test Task", ((TaskCreateDTO) result.getBody().getResponseObject()).getName());
    }

    @Test
    void deleteTaskByIdTest() {

        Long taskId = 1L;

        doNothing().when(taskService).deleteTaskById(taskId);

        ResponseEntity<Void> result = taskController.deleteTaskById(taskId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNull(result.getBody());
    }
}






