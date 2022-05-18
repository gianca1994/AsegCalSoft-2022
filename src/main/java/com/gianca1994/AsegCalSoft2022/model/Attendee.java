package com.gianca1994.AsegCalSoft2022.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
