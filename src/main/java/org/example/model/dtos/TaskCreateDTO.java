package org.example.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateDTO {

    private String name;
    private String description;
    @NotNull(message = "Due date cannot be null")
    private LocalDateTime dueDate;
    private String priority;
    @NotBlank(message = "Status cannot be blank")
    private String status;

}
