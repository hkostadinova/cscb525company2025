package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.validator.InvalidNames;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Company extends BaseEntity {

    @Column(name = "name")
    @InvalidNames(message = "The name is not valid!")
    @NotBlank(message = "Company name cannot be blank!")
    @Size(max = 20, message = "Company name has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Company name has to start with capital letter!")
    private String name;

    @Column(name = "initial_capital")
    @DecimalMin(value = "5000.00", message = "Initial capital has to be more than or equal to 5000.00", inclusive = true)
    @DecimalMax(value = "10000.00", message = "Initial capital has to be less than or equal to 10000.00")
    @Digits(integer = 5, fraction = 2, message = "Initial capital has to be with 5 digits before and 2 digits after the decimal separator!")
    private BigDecimal initialCapital;

    @Column(name = "foundation_date")
    @PastOrPresent(message = "Foundation date cannot be in the future!")
    private LocalDate foundationDate;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private Set<Employee> employees;
}
