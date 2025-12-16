package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="employee")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Employee extends BaseEntity {
    @Column(name="name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.PERSIST)
    private Set<Event> events;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private IdCard idCard;
}
