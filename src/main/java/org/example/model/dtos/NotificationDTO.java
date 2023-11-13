package org.example.model.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    private Long id;
    private String message;
    private LocalDate timestamp;
    private Long taskId;
}
