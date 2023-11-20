
import org.example.model.dtos.NotificationDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.model.entities.NotificationEntity;
import org.example.model.entities.TaskEntity;
import org.example.repository.NotificationRepository;
import org.example.service.NotificationMapper;
import org.example.service.NotificationService;
import org.example.service.TaskMapper;
import org.example.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private TaskService taskService;

    @Mock
    private NotificationMapper notificationMapper;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private NotificationService notificationService;

    public NotificationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllNotificationsTest() {

        List<NotificationEntity> mockNotifications = Collections.singletonList(new NotificationEntity());
        when(notificationRepository.findAll()).thenReturn(mockNotifications);
        when(notificationMapper.mapNotificationEntityToDTO(mockNotifications.get(0))).thenReturn(new NotificationDTO());

        List<NotificationDTO> result = notificationService.getAllNotifications();

        assertEquals(1, result.size());
    }

    @Test
    void createNotificationTest() {

        NotificationDTO inputDTO = new NotificationDTO();
        inputDTO.setMessage("Test Message");
        Long taskId = 1L;
        TaskSearchDTO taskSearchDTO = new TaskSearchDTO();
        taskSearchDTO.setId(taskId);

        when(taskService.findTaskById(taskId)).thenReturn(java.util.Optional.of(taskSearchDTO));

        NotificationEntity createdEntity = new NotificationEntity();
        createdEntity.setMessage("Test Message");
        createdEntity.setTask(new TaskEntity());

        when(notificationRepository.save(createdEntity)).thenReturn(createdEntity);
        when(notificationMapper.mapNotificationEntityToDTO(createdEntity)).thenReturn(inputDTO);

        NotificationDTO result = notificationService.createNotification(inputDTO, taskId);

        assertNotNull(result);
        assertEquals("Test Message", result.getMessage());
        assertNotNull(result.getTimestamp());
        assertEquals(taskId, result.getTaskId());
    }

    @Test
    void deleteAllNotificationsTest() {

        assertDoesNotThrow(() -> notificationService.deleteAllNotifications());
    }
}
