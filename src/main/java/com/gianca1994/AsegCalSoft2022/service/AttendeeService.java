package com.gianca1994.AsegCalSoft2022.service;

import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    public ArrayList<Attendee> getAttendees() {
        return (ArrayList<Attendee>) attendeeRepository.findAll();
    }

    public Attendee getAttendeeByDni(String dni) {
        try {
            return attendeeRepository.findAttendeeByDni(dni);
        } catch (Exception error) {
            return null;
        }
    }

    public Attendee saveAttendee(Attendee attendee) {
        if (attendee.getDni().matches("[+-]?\\d*(\\.\\d+)?") && attendee.getDni().length() > 0) {
            return attendeeRepository.save(attendee);
        }
        return null;
    }

    public void deleteAttendee(int id) {
        attendeeRepository.deleteById(id);
    }
}
