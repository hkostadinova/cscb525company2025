package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeIdCardDto {
    private long employeeId;
    private String name;
    private long idCardId;
    private String number;
}
