package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Position;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {
    private long id;
    private String name;
    private Position position;
}
