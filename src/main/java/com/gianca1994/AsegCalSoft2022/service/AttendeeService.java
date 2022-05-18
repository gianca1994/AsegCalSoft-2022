package com.gianca1994.AsegCalSoft2022.service;

import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    public ArrayList<Attendee> getAttendees() {
        return (ArrayList<Attendee>) attendeeRepository.findAll();
    }

    public Attendee getAttendeeByDni(String dni) {
        return attendeeRepository.findAttendeeByDni(dni);
    }

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public void deleteAttendee(String dni) {
        Attendee attendee = attendeeRepository.findAttendeeByDni(dni);
        attendeeRepository.deleteById(attendee.getId());
    }
}
