package com.gianca1994.AsegCalSoft2022.service;

import com.gianca1994.AsegCalSoft2022.dto.AttendeeDTO;
import com.gianca1994.AsegCalSoft2022.exception.EntityNotFoundException;
import com.gianca1994.AsegCalSoft2022.exception.EntityNotValidException;
import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    private boolean checkDNI(String dni) {
        String REGEX_DNI = "[+]?\\d*(\\.\\d+)?";
        return !dni.matches(REGEX_DNI) || dni.length() <= 0;
    }

    public ArrayList<Attendee> getAttendees() {
        return (ArrayList<Attendee>) attendeeRepository.findAll();
    }

    public Attendee getAttendeeByDni(String dni) {
        if (checkDNI(dni))
            throw new EntityNotValidException("Invalid DNI format");

        Attendee attendee = attendeeRepository.findAttendeeByDni(dni);
        if (attendee == null)
            throw new EntityNotFoundException("Assistant not found");

        return attendee;
    }

    public Attendee saveAttendee(AttendeeDTO attendee) {
        if (checkDNI(attendee.getDni()))
            throw new EntityNotValidException("Invalid DNI format");

        Attendee checkExistAttendee = attendeeRepository.findAttendeeByDni(attendee.getDni());
        if (checkExistAttendee != null)
            throw new EntityNotValidException("The assistant is already registered.");

        Attendee newAttendee = new Attendee();

        newAttendee.setDni(attendee.getDni());
        newAttendee.setName(attendee.getName());
        newAttendee.setSurname(attendee.getSurname());
        newAttendee.setPhone(attendee.getPhone());
        newAttendee.setBirthDate(attendee.getBirthDate());
        newAttendee.setDniScanUrl(attendee.getDniScanUrl());

        return attendeeRepository.save(newAttendee);
    }

    public ResponseEntity<Object> deleteAttendee(String dni) {
        if (checkDNI(dni))
            throw new EntityNotValidException("Invalid DNI format");

        Attendee deleteAttendee = attendeeRepository.findAttendeeByDni(dni);

        if (deleteAttendee != null) {
            attendeeRepository.delete(deleteAttendee);
            return new ResponseEntity<>("Attendee deleted correctly!", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Assistant not found");
        }
    }
}
