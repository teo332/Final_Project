package org.example.model.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class NotificationDTO {

    private String message;
    private String timestamp;
    private int taskId;
}
