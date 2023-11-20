package org.example.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

}
