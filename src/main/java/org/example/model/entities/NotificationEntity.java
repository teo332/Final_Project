package org.example.model.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationEntity {

    private String message;
    private String timestamp;
    private TaskEntity task;
}
