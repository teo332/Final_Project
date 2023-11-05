package org.example.model.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TaskDTO {

    private int id;
    private String name;
    private String description;
    private String dueDate;
    private String priority;
    private String status;

}
