package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="event")
@Getter
@Setter
@ToString(callSuper = true)
public class Event extends BaseEntity {
    private String name;
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Employee> employees;
}
