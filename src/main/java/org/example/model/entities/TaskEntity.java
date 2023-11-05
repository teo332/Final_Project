package org.example.model.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskEntity {

    private int id;
    private String name;
    private String description;
    private String dueDate;
    private String priority;
    private String status;
}
