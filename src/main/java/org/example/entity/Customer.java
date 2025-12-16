package org.example.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@DiscriminatorValue("customer")
//@PrimaryKeyJoinColumn(name="customer_id")
public class Customer extends Person {
    private String address;
}
