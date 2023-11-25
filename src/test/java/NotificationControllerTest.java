
import org.example.controller.NotificationController;
import org.example.model.dtos.NotificationDTO;
import org.example.model.dtos.TaskSearchDTO;
import org.example.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    public NotificationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllNotificationsTest() {

        List<NotificationDTO> mockNotifications = Collections.singletonList(new NotificationDTO());
        when(notificationService.getAllNotifications()).thenReturn(mockNotifications);
        List<NotificationDTO> result = notificationController.getAllNotifications().getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void createNotificationTest() {

        NotificationDTO inputDTO = new NotificationDTO();
        inputDTO.setMessage("Test Message");
        Long taskId = 1L;
        TaskSearchDTO taskSearchDTO = new TaskSearchDTO();
        taskSearchDTO.setId(taskId);
        when(notificationService.createNotification(inputDTO, taskId)).thenReturn(inputDTO);
        NotificationDTO result = notificationController.createNotification(inputDTO, taskId).getBody();
        assertNotNull(result);
        assertEquals("Test Message", result.getMessage());
    }

    @Test
    void deleteAllNotificationsTest() {

        ResponseEntity<String> result = notificationController.deleteAllNotifications();
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}

