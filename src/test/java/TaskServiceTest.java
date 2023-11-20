
import org.example.model.dtos.TaskCreateDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.model.entities.TaskEntity;
import org.example.repository.TaskRepository;
import org.example.service.TaskMapper;
import org.example.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    public TaskServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findTaskByIdTest() {

        Long taskId = 1L;
        TaskEntity mockTaskEntity = new TaskEntity();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTaskEntity));
        when(taskMapper.mapTaskEntityToUserSearchDTO(mockTaskEntity)).thenReturn(new TaskSearchDTO());

        Optional<TaskSearchDTO> result = taskService.findTaskById(taskId);

        assertTrue(result.isPresent());
    }

    @Test
    void findAllTasksTest() {

        List<TaskEntity> mockTaskEntities = Collections.singletonList(new TaskEntity());
        when(taskRepository.findAll()).thenReturn(mockTaskEntities);
        when(taskMapper.mapTaskEntityToUserSearchDTO(mockTaskEntities.get(0))).thenReturn(new TaskSearchDTO());

        List<TaskSearchDTO> result = taskService.findAllTasks();

        assertEquals(1, result.size());
    }

    @Test
    void deleteTaskByIdTest() {

        Long taskId = 1L;

        assertDoesNotThrow(() -> taskService.deleteTaskById(taskId));
    }

    @Test
    void createTaskTest() {

        TaskCreateDTO inputDTO = new TaskCreateDTO();
        inputDTO.setName("Test Task");
        TaskEntity createdEntity = new TaskEntity();
        createdEntity.setName("Test Task");

        when(taskMapper.mapTaskDTOtoTaskEntity(inputDTO)).thenReturn(createdEntity);
        when(taskRepository.save(createdEntity)).thenReturn(createdEntity);
        when(taskMapper.mapTaskEntityToTaskDTO(createdEntity)).thenReturn(inputDTO);

        TaskCreateDTO result = taskService.createTask(inputDTO);

        assertNotNull(result);
        assertEquals("Test Task", result.getName());
    }
}

