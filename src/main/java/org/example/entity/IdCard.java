package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class IdCard extends BaseEntity {
    private String number;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;
}
