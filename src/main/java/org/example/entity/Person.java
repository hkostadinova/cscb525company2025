package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "dType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("person")
public class Person extends BaseEntity {
    private String name;
    private LocalDate dateOfBirth;
}
