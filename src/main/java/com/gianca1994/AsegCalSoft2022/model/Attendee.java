package com.gianca1994.AsegCalSoft2022.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Attendee {

    @Id
    @Column(unique = true, updatable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String dniScanUrl;

    @Column(nullable = false)
    private boolean approved;
}
