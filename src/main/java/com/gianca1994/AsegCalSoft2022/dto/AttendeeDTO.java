package com.gianca1994.AsegCalSoft2022.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendeeDTO {
    private String dni;
    private String name;
    private String surname;
    private String phone;
    private String birthDate;
    private String dniScanUrl;
}
