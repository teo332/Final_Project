package org.example.model.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateDTO {

    private String name;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;

}
