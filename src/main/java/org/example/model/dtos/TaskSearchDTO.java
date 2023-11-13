package org.example.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskSearchDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;


}
